$(document).ready(function () {
    $("#ventana").modal('show');
});


function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    var userGoogle = profile.getEmail();
    var userGoogleImg = profile.getImageUrl();
    location.href = '../SignIn.do?userGoogle=' + userGoogle + '&userGoogleImg=' + userGoogleImg;
}

function fail(error) {
    alert("Fallo inicio de sesion google");
}