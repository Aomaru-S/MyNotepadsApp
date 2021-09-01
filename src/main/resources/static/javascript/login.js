function login() {
    let inputId = document.getElementById("input-id");
    let inputPassword = document.getElementById("input-password");

    let idForm = document.getElementById("id-form");
    let passwordForm = document.getElementById("password-form");

    idForm.value = inputId.innerText;
    passwordForm.value = inputPassword.innerText;

    document.form.submit();
}