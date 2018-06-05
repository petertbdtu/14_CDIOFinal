var Ingredient;
var Amount;
var CreateIngredient;
var ID;

function createComp() {
	Ingredient = document.getElementById("Ingredient");
	Amount = document.getElementById("Amount");
	CreateIngredient = '<select id=commodityRecipe> <option>' + Ingredient + '<option> råvare 1 <option> råvare 2 <option> råvare 3 </select> <label for="amountOfCommodities">Antal</label> <input type="text" id="amountOfCommodities" placeholder="' + Amount + '"> <button type="button" onclick="createComp()">Tilføj råvare</button> <br><br/>';
	document.getElementByID("IngredientForm").innerHTML += CreateIngredientForm;
	alert(Ingredient);
}

function createRecipe(ID) {
var ID = $("#recipeID").val();
}