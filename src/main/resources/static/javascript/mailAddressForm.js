function sendMail() {
    let inputMailAddress = document.getElementById("input-mail-address");

    let mailAddressForm = document.getElementById("mail-address-form");

    mailAddressForm.value = inputMailAddress.innerText;

    document.form.submit();
}

function enterIsSubmit() {
    document.getElementById('input-mail-address').addEventListener("keydown", ev => {
            if (ev.key === 'Enter') {
                sendMail();
            }
        })
}