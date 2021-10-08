function login() { document.form.submit();
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