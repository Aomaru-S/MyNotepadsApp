function submit(e) {
    document.getElementById("id-form").value = e.children.item(0).innerText;
    alert(document.getElementById(document.getElementById("id-form").value))
    // document.getElementById("edit-form").submit();
}