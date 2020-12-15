var d = document,
    itemBox = d.querySelectorAll('.text-box'),
    cartCont = d.getElementById('cart-content'),
    sum = 0,
    burgerCouter = 0;

getBurgerCount();
hideOrderButton();

d.getElementById('order-amount').innerHTML = burgerCouter;
function addEvent(elem, type, handler){
    if(elem.addEventListener){
        elem.addEventListener(type, handler, false);
    } else {
        elem.attachEvent('on'+type, function(){ handler.call( elem ); });
    }
    return false;
}
function getCartData(){
    return JSON.parse(localStorage.getItem('cart'));
}
function setCartData(o){
    localStorage.setItem('cart', JSON.stringify(o));
    return false;
}

function addToCart(e){
    this.disabled = true;
    var cartData = getCartData() || {},
        parentBox = this.parentNode,
        itemId = this.getAttribute('data-id'), // ID товара
        itemTitle = parentBox.querySelector('.title-burger-box').innerHTML,
        itemPrice = parentBox.querySelector('.burger-price').innerHTML;

    if(cartData.hasOwnProperty(itemId)){
        cartData[itemId][2] += 1;
        cartData[itemId][1] = parseInt(cartData[itemId][1]) + parseInt(itemPrice);
        burgerCouter += 1;
    } else {
        cartData[itemId] = [itemTitle, itemPrice, 1];
        burgerCouter += 1;
    }
    if(!setCartData(cartData)){
        this.disabled = false;
        d.getElementById('order-amount').innerHTML = burgerCouter;
        cartCont.innerHTML = '';
        hideOrderButton();
    }
    return false;
}
for(var i = 0; i < itemBox.length; i++){
    addEvent(itemBox[i].querySelector('.add-item'), 'click', addToCart);
}
function openCart(e){

    var cartData = getCartData(),
        amount = 0,
        sum = 0,
        totalItems = '';
    console.log(JSON.stringify(cartData));
    if(cartData !== null){
        totalItems = '<table class="shopping_list"><tr class="start-end-table-row"><th>Назва</th><th>Ціна</th><th>шт.</th></tr>';
        for(var items in cartData){
            totalItems += '<tr>';
            for(var i = 0; i < cartData[items].length; i++){
                totalItems += '<td>' + cartData[items][i] + '</td>';
                if (i === 1) {
                   sum += parseInt(cartData[items][i]);
                }
            }
            totalItems += '</tr>';
        }
        totalItems += '<tr class="start-end-table-row"><td>Разом</td><td>'+ sum + '</td><td>' + burgerCouter + '</td></tr>';
        totalItems += '<table>';
        cartCont.innerHTML = totalItems;
    }else {
        cartCont.innerHTML = '';
    }
    return false;
}

addEvent(d.getElementById('checkout'), 'click', openCart);
addEvent(d.getElementById('clear-cart'), 'click', function(e){
    localStorage.removeItem('cart');
    burgerCouter = 0;
    cartCont.innerHTML = '';
    d.getElementById('order-amount').innerHTML = burgerCouter;
    hideOrderButton();
});
function getBurgerCount() {
    var cartData = getCartData();
    if (cartData !== null) {
        for (var item in cartData) {
            burgerCouter += parseInt(cartData[item][2]);
        }
    }
}
function hideOrderButton() {
    if (burgerCouter === 0) {
        d.getElementById('form-ref').hidden = true;
    } else {
        d.getElementById('form-ref').hidden = false;
    }
}