var resultFieldNumber = document.getElementById('result-value');
var operatorField = document.getElementById('operator-value');
var historyFieldNumber = document.getElementById('history-value');
var arrayOfNumberElements = document.getElementsByClassName('numbers');
var arrayOfDefaultOperations = document.getElementsByClassName('default-btn');
var clear = document.getElementById('clear');
var equals = document.getElementById('equals');
var dot = document.getElementById('dot');

for (var i = 0; i < arrayOfNumberElements.length; i++) {
    arrayOfNumberElements[i].addEventListener("click", writeNumber);
}

for (var k = 0; k < arrayOfDefaultOperations.length; k++) {
    arrayOfDefaultOperations[k].addEventListener("click", writeOperator);
}

clear.addEventListener('click', clearAction);
dot.addEventListener('click', dotAction);
equals.addEventListener('click', equalsAction);


function writeNumber() {
    for (var count = 0; count < 10; count++) {
        if (this.id === count.toLocaleString()) {
            resultFieldNumber.innerText = resultFieldNumber.innerText.concat(this.id);
        }
    }
}

function writeOperator() {
    operatorField.innerText = this.id;
    historyFieldNumber.innerText = resultFieldNumber.innerText
    resultFieldNumber.innerText = "";
}

function clearAction() {
    resultFieldNumber.innerText = "";
    operatorField.innerText = "";
    historyFieldNumber.innerText = "";
}

function dotAction() {
    if (resultFieldNumber.innerText !== "" && !resultFieldNumber.innerText.includes('.')) {
        resultFieldNumber.innerText = resultFieldNumber.innerText.concat(".");
    }
}


function equalsAction() {
    if (historyFieldNumber.innerText !== "" && resultFieldNumber.innerText !== "") {
        switch (operatorField.innerText) {
            case "+":
                resultFieldNumber.innerText = (parseFloat(historyFieldNumber.innerText) + parseFloat(resultFieldNumber.innerText)).toLocaleString();
                break;
            case "-":
                resultFieldNumber.innerText = (parseFloat(historyFieldNumber.innerText) - parseFloat(resultFieldNumber.innerText)).toLocaleString();
                break;
            case "/":
                if (parseInt(resultFieldNumber.innerText) === 0) {
                    alert('Error: на ноль делить нельзя')
                    resultFieldNumber.innerText = "";
                    operatorField.innerText = "";
                    historyFieldNumber.innerText = "";
                    break;
                }
                resultFieldNumber.innerText = (parseFloat(historyFieldNumber.innerText) / parseFloat(resultFieldNumber.innerText)).toLocaleString();
                break;
            case "*":
                resultFieldNumber.innerText = (parseFloat(historyFieldNumber.innerText) * parseFloat(resultFieldNumber.innerText)).toLocaleString();
                break;
        }
    } else {
        alert('Error: для работы необходимо два числа');
        resultFieldNumber.innerText = "";
        operatorField.innerText = "";
        historyFieldNumber.innerText = "";
    }
    historyFieldNumber.innerText = "";
    operatorField.innerText = "";
}