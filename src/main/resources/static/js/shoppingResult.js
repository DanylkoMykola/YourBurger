



getShoppingResult();
function getCartData(){
    return JSON.parse(localStorage.getItem('cart'));
}

function getBurgerCount() {
    var cartData = getCartData(),
        burgerCount = 0;
    if (cartData !== null) {
        for (var item in cartData) {
            burgerCount += parseInt(cartData[item][2]);
        }
        return burgerCount;
    } else {
        return 0;
    }
}

function getShoppingResult() {

    var cartData = getCartData(),
        totalItems = '',
        burgerCount = getBurgerCount(),
        cartRes = document.getElementById('cart-result'),
        sum = 0;

    console.log(JSON.stringify(cartData));
    console.log('Hello pidar');
    if (cartData != null) {
        totalItems = '<table class=><tr "><th>Назва</th><th>Ціна</th><th>кількість</th></tr>';
        for (var items in cartData) {
            totalItems += '<tr>';
            for (var i = 0; i < cartData[items].length; i++) {
                totalItems += '<td>' + cartData[items][i] + '</td>';
                if (i === 1) {
                    sum += parseInt(cartData[items][i]);
                }
            }
            totalItems += '</tr>'
        }
        totalItems += '<tr class="cart-result-table-row"><td>Разом</td><td>' + sum + '</td><td>'+ burgerCount +'</td></tr>';
        totalItems += '</table>';
        cartRes.innerHTML = totalItems;
    } else {
        cartRes.innerHTML = '';
    }
    return false;
}

