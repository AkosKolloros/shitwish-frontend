let cartModalButton = document.getElementById("cart-modal-button");
cartModalButton.addEventListener('click', function () {
    if (document.getElementById("itemNumber").innerText !== "0") {
        $('#shoppingCart').modal('show');
    }
});
let addOnceItemForms = document.querySelectorAll("form.addOnce");
handleAddRemoveEvent(addOnceItemForms);

setAddRemoveButtonListeners();

function getCartItem(form) {
    let formObject = getFormFieldsAsObject(form);
    return {
        price: formObject.price,
        productId: formObject.productId,
        name: formObject.name,
        cart: {
            id: formObject.id
        }
    };
}

function getUrl(form) {
    let url;
    let formObject = getFormFieldsAsObject(form);
    if (formObject.action === "add") {
        url = "/cart/add-item";
    } else {
        url = "/cart/delete-item";
    }

    return url;
}

function handleAddRemoveEvent(formItems) {
    for (let i = 0; i < formItems.length; i++) {
        let form = formItems[i];
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            event.stopPropagation();
            let cartItem = getCartItem(form);
            fetch(getUrl(form), {
                method: "post",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(cartItem)
            }).then(function (response) {
                if (response.ok) {
                    return response.json();
                } else {
                    response.error();
                }
            }).then(function (data) {
                if (data.products.length > 0) {
                    console.log(data);
                    fillAndAppendCartTemplate(data);
                } else {
                    document.getElementById("itemNumber").innerText = "0";
                    $('#shoppingCart').modal('hide');
                }
            }).catch(function (error) {
                alert(`Error: ${error}\nIf you see this, our testers did a sloppy job, and our developers an even sloppier`)
            });
        });
    }
}

function setAddRemoveButtonListeners() {

    let addItemForms = document.querySelectorAll("form.add");
    let removeItemForms = document.querySelectorAll("form.remove");
    handleAddRemoveEvent(addItemForms);
    handleAddRemoveEvent(removeItemForms);
}

function getFormFieldsAsObject(elements) {
    return [].reduce.call(elements, (data, element) => {

        data[element.name] = element.value;
        return data;

    }, {});
}

function fillAndAppendCartTemplate(data) {
    let cartSource = document.getElementById("cartBody").innerHTML;
    let cartTemplate = Handlebars.compile(cartSource);
    let items = data.products;
    for (let item of items) {
        item["cartId"] = data.id;
    }
    let cartContext = {items: items, amount: data.amountToPay};
    let placeToInsertCart = document.getElementById("cart");
    let toAppendCart = cartTemplate(cartContext);
    placeToInsertCart.innerHTML = toAppendCart;
    let itemNumber = data.productNumber;
    document.getElementById("itemNumber").innerText = itemNumber;
    let cartIds = document.getElementsByClassName("cartId")
    for (element of cartIds) {
        element.setAttribute("value", data.id);
    }
    setAddRemoveButtonListeners();
}