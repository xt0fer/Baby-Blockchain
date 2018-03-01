import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlockchainTest {

    @org.junit.Test
    public void TestBlockChainAdd() {
        Block genesisBlock = new Block().mine();
        Blockchain chain = new Blockchain();

        chain.addBlock(genesisBlock);
        Block p = chain.getBlock(0);

        assertTrue(genesisBlock.equals(p));
    }

    @org.junit.Test
    public void TestBlockChain2() {
        // test iterator
        Block genesisBlock = new Block().mine();

        Blockchain chain = new Blockchain();

        chain.addBlock(genesisBlock);
        Iterator iter = chain.iterator();

        assertTrue(iter.hasNext());

        Block b = (Block) iter.next();
        assertFalse(iter.hasNext());

        assertTrue(genesisBlock.equals(b));
    }



}
