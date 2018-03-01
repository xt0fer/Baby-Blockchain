import static org.junit.Assert.assertTrue;

public class BlockTest {

    @org.junit.Test
    public void TestBlock() {
        String expected = "00fb6ab0677a641f75990fe81362bfe16d931d5dc54a0f9ff3b973adc42abf13";

        Block genesisBlock = new Block().mine();

        String actual = Hash.hexString(genesisBlock.getBlockHash());

        assertTrue(actual.equals(expected));
    }

    @org.junit.Test
    public void TestVerify() {
        String expected = "00fb6ab0677a641f75990fe81362bfe16d931d5dc54a0f9ff3b973adc42abf13";

        Block genesisBlock = new Block().mine();

        String actual = Hash.hexString(genesisBlock.getBlockHash());

        assertTrue(actual.equals(expected));
        // verifyBlock
        assertTrue(genesisBlock.verifyBlock());
    }

}
