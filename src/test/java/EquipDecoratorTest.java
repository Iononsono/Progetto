import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class EquipDecoratorTest {

    private static class TestEquipDecorator extends EquipDecorator {
        public TestEquipDecorator(Statistiche componente) {
            super(componente);
        }
    }

    @Test
    public void testEquipDecoratorDelegation() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10.0f, 8.0f, 5.0f);


        TestEquipDecorator decorator = new TestEquipDecorator(bs);


        assertEquals(10.0f, decorator.getHp());
        assertEquals(8.0f, decorator.getAtk());
        assertEquals(5.0f, decorator.getMp());
    }

    @Test
    public void testEquipDecoratorWithDifferentComponent() {

        BaseStats bs = new BaseStats("Mago");
        bs.sommaStats(5.0f, 7.0f, 10.0f);

        TestEquipDecorator decorator = new TestEquipDecorator(bs);

        assertEquals(5.0f, decorator.getHp());
        assertEquals(7.0f, decorator.getAtk());
        assertEquals(10.0f, decorator.getMp());
    }

    @Test
    public void testEquipDecoratorConstructor() {
        BaseStats bs = new BaseStats("Arciere");
        bs.sommaStats(6.0f, 6.0f, 10.0f);

        TestEquipDecorator decorator = new TestEquipDecorator(bs);
        assertNotNull(decorator);


        assertEquals(6.0f, decorator.getHp());
    }
}