function signUp() {
    let inputUserName = document.getElementById("input-username");
    let inputPassword = document.getElementById("input-password");

    let userNameForm = document.getElementById("username-form");
    let passwordForm = document.getElementById("password-form");

    userNameForm.value = inputUserName.innerText;
    passwordForm.value = inputPassword.innerText;

    document.form.submit();
}

function enterIsLogin() {
    let ids = [].slice.call(arguments);
    ids.forEach(
        id => document.getElementById(id).addEventListener("keydown", ev => {
            if (ev.key === 'Enter') {
                login();
            }
        })
    );
}