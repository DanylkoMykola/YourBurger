var cartRes = document.getElementById('cart-result');

function getShoppingResult() {

    var cartData = getCartData(),
        totalItems = '',
        sum = 0;

    console.log(JSON.stringify(cartData));
    if (cartData != null) {
        totalItems = '<table class="shopping-result"><tr calss="cart-result-table-row"><th>Назва</th><th>Ціна</th><th>кількість</th></tr>';
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
        totalItems += '<tr class="cart-result-table-row"><td>Разом</td><td>' + sum + '</td><td>'+ burgerCouter +'</td></tr>';
        totalItems += '</table>'
        cartRes.innerHTML = totalItems;
    } else {
        cartRes.innerHTML = '';
    }
    return false;
}

