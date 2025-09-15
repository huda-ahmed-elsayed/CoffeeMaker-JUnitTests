package coffeemaker.tests;

import coffeemaker.*;
import coffeemaker.exceptions.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Comprehensive test suite for the CoffeeMaker project.
 * Covers Recipe, Inventory, RecipeBook, CoffeeMaker, and Main (UI).
 */
public class CoffeeMakerTest {

    private CoffeeMaker coffeeMaker;
    private Recipe recipe1, recipe2, recipe3, recipe4;

    // =========================================================
    // Setup
    // =========================================================

    @Before
    public void setUp() throws Exception {
        coffeeMaker = new CoffeeMaker();

        recipe1 = createRecipe("Coffee", "50", "3", "1", "1", "0");
        recipe2 = createRecipe("Latte", "75", "2", "2", "1", "0");
        recipe3 = createRecipe("Mocha", "90", "1", "1", "2", "2");
        recipe4 = createRecipe("Hot Chocolate", "65", "0", "1", "1", "4");

        coffeeMaker.addRecipe(recipe1);
    }

    private Recipe createRecipe(String name, String price,
                                String coffee, String milk,
                                String sugar, String chocolate) throws RecipeException {
        Recipe r = new Recipe();
        r.setName(name);
        r.setPrice(price);
        r.setAmtCoffee(coffee);
        r.setAmtMilk(milk);
        r.setAmtSugar(sugar);
        r.setAmtChocolate(chocolate);
        return r;
    }

    // =========================================================
    // Recipe Tests
    // =========================================================

    @Test
    public void testRecipeEqualsAndHashCode() throws RecipeException {
        Recipe same = createRecipe("Coffee", "50", "3", "1", "1", "0");
        assertEquals(recipe1, same);
        assertEquals(recipe1.hashCode(), same.hashCode());
    }

    @Test
    public void testRecipeToString() {
        assertEquals("Coffee", recipe1.toString());
    }

    @Test
    public void testSettersValidValues() throws RecipeException {
        Recipe r = createRecipe("Test", "10", "2", "1", "3", "0");
        assertEquals(10, r.getPrice());
        assertEquals(2, r.getAmtCoffee());
        assertEquals(1, r.getAmtMilk());
        assertEquals(3, r.getAmtSugar());
        assertEquals(0, r.getAmtChocolate());
    }
    
    @Test
    public void testSettersWithZero() throws RecipeException {
        Recipe r = new Recipe();
        r.setPrice("0");
        r.setAmtCoffee("0");
        r.setAmtMilk("0");
        r.setAmtSugar("0");
        r.setAmtChocolate("0");

        assertEquals(0, r.getPrice());
        assertEquals(0, r.getAmtCoffee());
        assertEquals(0, r.getAmtMilk());
        assertEquals(0, r.getAmtSugar());
        assertEquals(0, r.getAmtChocolate());
    }
    
    @Test
    public void testSettersWithNegative() throws RecipeException {
        Recipe r = new Recipe();
        r.setPrice("-1");
        r.setAmtCoffee("-2");
        r.setAmtMilk("-1");
        r.setAmtSugar("-3");
        r.setAmtChocolate("-4");

        assertEquals(0, r.getPrice());
        assertEquals(0, r.getAmtCoffee());
        assertEquals(0, r.getAmtMilk());
        assertEquals(0, r.getAmtSugar());
        assertEquals(0, r.getAmtChocolate());
    }

    @Test(expected = RecipeException.class)
    public void testInvalidPriceThrowsException() throws RecipeException {
        Recipe r = new Recipe();
        r.setPrice("abc");
    }

    @Test(expected = RecipeException.class)
    public void testInvalidCoffeeAmountThrowsException() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtCoffee("-1");
    }

    @Test(expected = RecipeException.class)
    public void testInvalidMilkAmountThrowsException() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtMilk("abc");
    }

    @Test(expected = RecipeException.class)
    public void testInvalidSugarAmountThrowsException() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtSugar("-5");
    }

    @Test(expected = RecipeException.class)
    public void testInvalidChocolateAmountThrowsException() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtChocolate("xyz");
    }
    
    @Test(expected = RecipeException.class)
    public void testSetAmtCoffeeNegativeThrows() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtCoffee("coff");
    }

    @Test(expected = RecipeException.class)
    public void testSetAmtMilkNegativeThrows() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtMilk("-1");
    }

    @Test(expected = RecipeException.class)
    public void testSetAmtSugarNegativeThrows() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtSugar("abs");
    }

    @Test(expected = RecipeException.class)
    public void testSetAmtChocolateNegativeThrows() throws RecipeException {
        Recipe r = new Recipe();
        r.setAmtChocolate("-1");
    }
    
    @Test
    public void testEqualsDifferentCases() throws RecipeException {
        Recipe r1 = createRecipe("Coffee", "50", "3", "1", "1", "0");
        Recipe r2 = createRecipe("Latte", "50", "3", "1", "1", "0");
        Recipe r3 = null;
        Recipe r4 = new Recipe();
        r4.setName(null);

        assertFalse(r1.equals(r2));       // different name
        assertFalse(r1.equals(r3));       // null comparison
        assertFalse(r1.equals("string")); // different class
        assertTrue(r4.equals(new Recipe())); // both names null
    }
    
    @Test
    public void testEqualsNameNullOtherNotNull() throws RecipeException {
        Recipe r1 = new Recipe(); // name == null
        Recipe r2 = createRecipe("Latte", "50", "1", "1", "1", "1");
        assertFalse(r1.equals(r2)); // null name vs non-null name
    }
    
    @Test
    public void testEqualsSameObject() {
        assertTrue(recipe1.equals(recipe1));
    }

    @Test
    public void testHashCodeWithNullName() {
        Recipe r = new Recipe();
        r.setName(null);
        assertNotNull(r.hashCode()); // ensure it doesn’t throw NPE
    }
    
    @Test
    public void testHashCodeNonNullName() throws RecipeException {
        Recipe r = createRecipe("Mocha", "50", "1", "1", "1", "1");
        int code = r.hashCode();
        assertTrue(code != 0);
    }
    
    @Test
    public void testHashCodeAllBranches() throws RecipeException {
        // name == null
        Recipe rNull = new Recipe();
        rNull.setName(null);
        int codeNull = rNull.hashCode();
        assertNotNull(codeNull);

        // name != null
        Recipe rNonNull = createRecipe("Espresso", "50", "1", "1", "1", "1");
        int codeNonNull = rNonNull.hashCode();
        assertTrue(codeNonNull != 0);
    }
    
    @Test
    public void testEqualsThisNameNonNullOtherNameNull() throws RecipeException {
        Recipe r1 = createRecipe("Mocha", "50", "1", "1", "1", "1"); // name != null
        Recipe r2 = new Recipe(); // name == null
        r2.setName(null);

        assertFalse(r1.equals(r2)); // triggers the else-if branch
    }
    
    // =========================================================
    // RecipeBook Tests
    // =========================================================

    @Test
    public void testGetRecipesArray() {
        Recipe[] recipes = coffeeMaker.getRecipes();
        assertEquals("Coffee", recipes[0].getName());
    }

    @Test
    public void testAddDuplicateRecipeFails() throws RecipeException {
        Recipe duplicate = createRecipe("Coffee", "50", "3", "1", "1", "0");
        assertFalse(coffeeMaker.addRecipe(duplicate));
    }

    @Test
    public void testAddRecipeWhenFullFails() throws RecipeException {
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        coffeeMaker.addRecipe(recipe4);

        Recipe extra = createRecipe("Extra", "5", "1", "1", "1", "0");
        assertFalse(coffeeMaker.addRecipe(extra));
    }

    @Test
    public void testEditRecipeValuesUpdated() throws RecipeException {
        Recipe newRecipe = createRecipe("Mocha", "90", "1", "1", "2", "2");

        String oldName = coffeeMaker.editRecipe(0, newRecipe);
        assertEquals("Coffee", oldName);

        Recipe[] recipes = coffeeMaker.getRecipes();
        assertEquals("", recipes[0].getName());  // Implementation quirk
        assertEquals(90, recipes[0].getPrice());
        assertEquals(1, recipes[0].getAmtCoffee());
        assertEquals(1, recipes[0].getAmtMilk());
        assertEquals(2, recipes[0].getAmtSugar());
        assertEquals(2, recipes[0].getAmtChocolate());
    }
    
    @Test
    public void testEditRecipeWhenNullReturnsNull() throws RecipeException {
        // Ensure the position is empty
        Recipe[] recipes = coffeeMaker.getRecipes();
        recipes[2] = null;  // just to be explicit
        Recipe newRecipe = createRecipe("Latte", "50", "1", "1", "1", "1");

        String result = coffeeMaker.editRecipe(2, newRecipe); // position empty
        assertNull(result);  // else branch is executed
    }

    @Test
    public void testDeleteRecipeWhenExists() throws RecipeException {
        Recipe[] recipes = coffeeMaker.getRecipes();
        recipes[1] = createRecipe("Mocha", "50", "1", "1", "1", "1");

        String deletedName = coffeeMaker.deleteRecipe(1);
        assertEquals("Mocha", deletedName);
        assertEquals("", recipes[1].getName()); // new Recipe created in delete
    }

    @Test
    public void testDeleteRecipeWhenNullReturnsNull() {
        Recipe[] recipes = coffeeMaker.getRecipes();
        recipes[3] = null; // empty slot

        String result = coffeeMaker.deleteRecipe(3); // position empty
        assertNull(result); // else branch executed
    }

    // =========================================================
    // Inventory Tests
    // =========================================================

    static class TestInventory extends Inventory {
        public boolean callEnoughIngredients(Recipe r) {
            return super.enoughIngredients(r);
        }
    }

    @Test
    public void testEnoughIngredientsTrue() {
        TestInventory inv = new TestInventory();
        assertTrue(inv.callEnoughIngredients(recipe1));
    }

    @Test
    public void testEnoughIngredientsFalse() throws RecipeException {
        TestInventory inv = new TestInventory();
        Recipe r = createRecipe("StrongCoffee", "50", "100", "0", "0", "0");
        assertFalse(inv.callEnoughIngredients(r));
    }
    
    @Test
    public void testEnoughIngredientsFalseForMilk() throws RecipeException {
        TestInventory inv = new TestInventory();
        Recipe r = createRecipe("MilkHeavy", "50", "0", "100", "0", "0");
        assertFalse(inv.callEnoughIngredients(r));
    }

    @Test
    public void testEnoughIngredientsFalseForSugar() throws RecipeException {
        TestInventory inv = new TestInventory();
        Recipe r = createRecipe("SugarHeavy", "50", "0", "0", "100", "0");
        assertFalse(inv.callEnoughIngredients(r));
    }

    @Test
    public void testEnoughIngredientsFalseForChocolate() throws RecipeException {
        TestInventory inv = new TestInventory();
        Recipe r = createRecipe("ChocoHeavy", "50", "0", "0", "0", "100");
        assertFalse(inv.callEnoughIngredients(r));
    }

    @Test(expected = InventoryException.class)
    public void testInventoryExceptionThrown() throws InventoryException {
        coffeeMaker.addInventory("a", "1", "1", "1");
    }
    
    @Test
    public void AddSugarValid() throws InventoryException {
    	Inventory inv = new Inventory();
    	inv.addSugar("3");
    	assertEquals(18, inv.getSugar());
    }
    
    @Test(expected = InventoryException.class)
    public void testAddSugarNegativeThrowsException() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addSugar("-2");
    }

    @Test(expected = InventoryException.class)
    public void testAddSugarNonNumericThrowsException() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addSugar("tss");
    }
    
    @Test
    public void testAddMilkValid() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addMilk("4");
        assertEquals(19, inv.getMilk());
    }

    @Test(expected = InventoryException.class)
    public void testAddMilkNegativeThrowsException() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addMilk("-5");
    }

    @Test(expected = InventoryException.class)
    public void testAddMilkNonNumericThrowsException() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addMilk("coww");
    }

    
    @Test
    public void testAddCoffeeValid() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addCoffee("5");
        assertEquals(20, inv.getCoffee());
    }

    @Test(expected = InventoryException.class)
    public void testAddCoffeeNegativeThrowsException() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addCoffee("-3");
    }

    @Test(expected = InventoryException.class)
    public void testAddCoffeeNonNumericThrowsException() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addCoffee("abc");
    }
    
    @Test
    public void AddChocolateValid() throws InventoryException {
    	Inventory inv = new Inventory();
    	inv.addChocolate("2");
    	assertEquals(17, inv.getChocolate());
    }
    
    @Test(expected = InventoryException.class)
    public void testAddChocolateNegativeThrowsException() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addChocolate("-1");
    }

    @Test(expected = InventoryException.class)
    public void testAddChocolateNonNumericThrowsException() throws InventoryException {
        Inventory inv = new Inventory();
        inv.addChocolate("oops");
    }

    @Test
    public void testAddInventorySuccess() throws InventoryException {
        coffeeMaker.addInventory("1", "1", "0", "1");
        String inventory = coffeeMaker.checkInventory();
        assertTrue(inventory.contains("Coffee"));
        assertTrue(inventory.contains("Milk"));
        assertTrue(inventory.contains("Chocolate"));
    }

    // =========================================================
    // CoffeeMaker Tests
    // =========================================================

    @Test
    public void testMakeCoffee() {
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }

    @Test
    public void testMakeCoffeeInsufficientFunds() {
        assertEquals(10, coffeeMaker.makeCoffee(0, 10));
    }

    @Test
    public void testMakeCoffeeNotEnoughIngredients() throws RecipeException {
        Recipe big = createRecipe("BigCoffee", "50", "500", "0", "0", "0");
        coffeeMaker.addRecipe(big);
        assertEquals(100, coffeeMaker.makeCoffee(1, 100));
    }

    @Test
    public void testMakeCoffeeExactChange() {
        assertEquals(0, coffeeMaker.makeCoffee(0, 50));
    }
    
    @Test
    public void testMakeCoffeeWithNullRecipe() {
        // No recipe was added at index 2, so it's null
        int change = coffeeMaker.makeCoffee(2, 100);
        assertEquals(100, change); // should return all money back
    }

    @Test
    public void testSequenceAddRecipeThenPurchase() {
        coffeeMaker.addRecipe(recipe2);
        int change = coffeeMaker.makeCoffee(1, 100);
        assertTrue(change >= 0);
        assertTrue(coffeeMaker.checkInventory().contains("Coffee"));
    }

    @Test
    public void testSequenceDeleteRecipeThenTryPurchase() {
        coffeeMaker.deleteRecipe(0);
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));
    }

    @Test
    public void testSequenceEditRecipe() throws RecipeException {
        Recipe newRecipe = createRecipe("Mocha", "80", "2", "1", "1", "2");
        coffeeMaker.editRecipe(0, newRecipe);
        int change = coffeeMaker.makeCoffee(0, 100);
        assertTrue(change >= 0);
    }

    // =========================================================
    // Main (UI) Tests
    // =========================================================

    public class MainTest {

        private final InputStream originalIn = System.in;
        private final PrintStream originalOut = System.out;
        private ByteArrayInputStream testIn;
        private ByteArrayOutputStream testOut;

        @Before
        public void setUp() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void tearDown() {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        private void provideInput(String data) {
            testIn = new ByteArrayInputStream(data.getBytes());
            System.setIn(testIn);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @Test
        public void testMainExit() {
            // Provide input "0" to exit
            provideInput("0\n");
            try {
                Main.main(new String[]{});
            } catch (SecurityException e) {
                // ignore if System.exit is called
            }
            String output = getOutput();
            assertTrue(output.contains("Welcome to the CoffeeMaker!"));
            assertTrue(output.contains("1. Add a recipe"));
            assertTrue(output.contains("0. Exit"));
        }

        @Test
        public void testInvalidInput() {
            // Provide invalid input then exit
            provideInput("abc\n0\n");
            try {
                Main.main(new String[]{});
            } catch (SecurityException e) {
                // ignore System.exit
            }
            String output = getOutput();
            assertTrue(output.contains("Please enter a number from 0 - 6"));
        }

        @Test
        public void testOutOfRangeInput() {
            // Input number outside 0-6
            provideInput("9\n0\n");
            try {
                Main.main(new String[]{});
            } catch (SecurityException e) {
            }
            String output = getOutput();
            assertTrue(output.contains("Please enter a number from 0 - 6"));
        }
    }
}