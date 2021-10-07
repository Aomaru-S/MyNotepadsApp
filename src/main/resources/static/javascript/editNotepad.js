let categoryList = [];

function saveNotepad() {
    let titleEditor = document.getElementById("title-editor");
    let bodyEditor = document.getElementById("body-editor");

    let titleForm = document.getElementById("title-form");
    let bodyForm = document.getElementById("body-form");

    titleForm.value = titleEditor.innerText;
    bodyForm.value = bodyEditor.innerText;

    let dForm = document.getElementById('form');
    categoryList.forEach(function (e) {
        let input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'category';
        input.value = e;
        dForm.insertBefore(input, dForm.firstChild);
    })

    document.form.submit();
}

function resetNotepad() {
    let titleEditor = document.getElementById("title-editor");
    let bodyEditor = document.getElementById("body-editor");

    titleEditor.innerText = null;
    bodyEditor.innerText = null;
}

function addCategory() {
    let categoryListContainer = document.getElementById('category-list-container');
    let categoryEditor = document.getElementById('category-editor');
    if (!categoryEditor.innerText) {
        return;
    }
    categoryList.push(categoryEditor.innerText);
    let newCategoryContainer = document.createElement('div');
    newCategoryContainer.classList.add('category-container');
    let newCategory = document.createElement('div');
    newCategory.innerText = categoryEditor.innerText;
    let removeCategory = document.createElement('div');
    removeCategory.innerText = '×';
    removeCategory.addEventListener('click', function () {
        removeCategoryFun(newCategory.innerText);
    }, false);
    newCategoryContainer.insertBefore(removeCategory, newCategoryContainer.firstChild)
    newCategoryContainer.insertBefore(newCategory, newCategoryContainer.firstChild)
    categoryListContainer.insertBefore(newCategoryContainer, categoryListContainer.firstChild);
    categoryEditor.innerText = null;
}

function removeCategoryFun(category) {
    let index = categoryList.indexOf(category);
    categoryList.splice(index, 1);
    let categoryListContainer = document.getElementById('category-list-container');
    categoryListContainer.removeChild(categoryListContainer.children[categoryListContainer.childElementCount - index - 1]);
}

window.onload = function () {
    let list = document.getElementById("hidden-category-list");
    let count = list.childElementCount;
    for (let i = 0; i < count; i++) {
        document.getElementById("category-editor").innerText = list.children.item(i).value;
        addCategory();
    }
}