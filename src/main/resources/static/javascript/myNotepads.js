function submit(e) {
    document.getElementById("id-form").value = e.children.item(0).innerText;
    document.getElementById("edit-form").submit();
}