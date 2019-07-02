function atualizarStates (html, stateToSelectAfter) {
    $('#state-fragment').replaceWith(html).change();
    $('#state-fragment select').selectpicker('val', stateToSelectAfter ? stateToSelectAfter : "");
    addStateListeners();
}

function atualizarCities (html, cityToSelectAfter) {
    $('#city-fragment').replaceWith(html).change();
    $('#city-fragment select').selectpicker('val', cityToSelectAfter ? cityToSelectAfter : "");
    addCityListeners();
}

function obterStates(event, country, stateToSelectAfter) {
    if(event)event.preventDefault();
    var data = '&obterStates=' + country;
    $.get(window.location.pathname, data, function(data) {
        atualizarStates(data, stateToSelectAfter);
    });
}

function obterCities(event, state, cityToSelectAfter) {
    if(event)event.preventDefault();
    var data = '&obterCities=' + state;
    $.get(window.location.pathname, data, function(data) {
        atualizarCities(data, cityToSelectAfter);
    });
}

function hideStates() {
    $('#state-fragment').empty()
}

function hideCities() {
    $('#city-fragment').empty();
}

function addCountryListeners() {
    $('#country-fragment select').change(function (event) {
        $('#hidden-country').val($(this).val()).change();
        $('#hidden-state').val("").change();
        $('#hidden-city').val("").change();
        hideStates();
        hideCities();
        obterStates(event, $(this).val(), null);
    });
}

function addStateListeners() {
    $('#state-fragment select').change(function (event) {
        $('#hidden-state').val($(this).val()).change();
        $('#hidden-city').val("").change();
        hideCities();
        obterCities(event, $(this).val(), null);
    });
}

function addCityListeners() {
    $('#city-fragment select').change(function (event) {
        $('#hidden-city').val($(this).val()).change();
    });
}

function refreshInitialValues() {
    var country = $('#hidden-country').val();
    if (country) {
        $('#country-fragment select').selectpicker('val', country);
    } else {
        $('#country-fragment select').selectpicker('val', "");
        hideStates();
        hideCities();
        return;
    }

    var state = $('#hidden-state').val();
    if (state) {
        obterStates(null, country, state);
    } else {
        hideCities();
        obterStates(null, country, "");
        return;
    }

    var city = $('#hidden-city').val();
    if (city){
        obterCities(null, state, city);
    } else {
        obterCities(null, state, "");
    }
}

refreshInitialValues();
addCountryListeners();
addStateListeners();
addCityListeners();