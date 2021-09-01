function saveNotepad() {
    let titleEditor = document.getElementById("title-editor");
    let bodyEditor = document.getElementById("body-editor");

    let titleForm = document.getElementById("title-form");
    let bodyForm = document.getElementById("body-form");

    titleForm.value = titleEditor.innerText;
    bodyForm.value = bodyEditor.innerText;

    document.form.submit();
}

function resetNotepad() {
    let titleEditor = document.getElementById("title-editor");
    let bodyEditor = document.getElementById("body-editor");

    titleEditor.innerText = null;
    bodyEditor.innerText = null;
}