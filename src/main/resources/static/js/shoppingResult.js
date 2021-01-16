


getShoppingResult();
function getCartData(){
    return JSON.parse(localStorage.getItem('cart'));
}

function getBurgerCount() {
    let cartData = getCartData(),
        burgerCount = 0;
    if (cartData !== null) {
        for (let item in cartData) {
            burgerCount += parseInt(cartData[item][2]);
        }
        return burgerCount;
    } else {
        return 0;
    }
}

function getShoppingResult() {

    let cartData = getCartData(),
        totalItems = '',
        inputItems = '',
        burgerCount = getBurgerCount(),
        cartRes = document.getElementById('cart-result'),
        cartInput = document.getElementById('cart-result-input'),
        sum = 0,
        cartContentArr = [];


    console.log(JSON.stringify(cartData));

    if (cartData != null) {
        totalItems = '<table class=><tr "><th>Назва</th><th>Ціна</th><th>Кількість</th></tr>';
        for (let items in cartData) {
            totalItems += '<tr>';
            let cartContent = {
                name: '',
                price: 0,
                count: 0,
            };
            console.log(cartData[items].length);
            for (let i = 0; i < cartData[items].length; i++) {
                let cartTmp = cartData[items][i];
                totalItems += '<td>' + cartTmp + '</td>';
                
                if (i === 0) {
                    cartContent.name = cartTmp;
                }
                if (i === 1) {
                    sum += parseInt(cartTmp);
                    cartContent.price = cartTmp;
                }

                if (i === 2) {
                    cartContent.count = cartTmp
                }
            }
            totalItems += '</tr>';
            cartContentArr.push(cartContent);
        }
        totalItems += '<tr class="cart-result-table-row"><td>Разом</td><td>' + sum + '</td><td>'+ burgerCount +'</td></tr>';
        totalItems += '</table>';
        inputItems = '<input type="hidden" id="order-list" name="orderList" value="'
            + JSON.stringify(cartContentArr).replace(/"/g, '&qout') + '" >';
        cartRes.innerHTML = totalItems;
        cartInput.innerHTML = inputItems;
    } else {
        cartRes.innerHTML = '';
        cartInput.innerHTML = '';
    }
    return false;
}

