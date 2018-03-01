import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Hash = digital signature
     *
     * Each block will have:
     *      List of transactions
     *      Previous hash
     *      Hash
     *
     */

    public static void main(String[] args) {
        Blockchain chain = new Blockchain();
        byte[] initialHash = "zipcoder".getBytes();

        System.out.println("Initial hash");
        System.out.println(Hash.hexString(initialHash));

        String[] genesisTransactions = {"Received $500", "Send $10"};
        Block genesisBlock = new Block(initialHash, genesisTransactions).mine();
        chain.addBlock(genesisBlock);

        String[] block2Transaction = {"Received $20", "Send $5"};
        Block block2 = new Block(genesisBlock.getBlockHash(), block2Transaction).mine();
        chain.addBlock(block2);

        String[] block3Transaction = {"Received $1000", "Send $50"};
        Block block3 = new Block(block2.getBlockHash(), block3Transaction).mine();
        chain.addBlock(block3);

        System.out.println("Hash of genesis block;");
        System.out.println(genesisBlock.getBlockHashString());

        System.out.println("Hash of block 2");
        System.out.println(block2.getBlockHashString());
        System.out.println(Hash.hexString(block2.getPreviousHash()));

        System.out.println("Hash of block 3");
        System.out.println(block3.getBlockHashString());
        System.out.println(Hash.hexString(block3.getPreviousHash()));

        System.out.println("\n===\nBlockchain:\n");
        Iterator<Block> depthFirst = chain.iterator();
        while (depthFirst.hasNext()) {
            Block b = depthFirst.next();
            System.out.println(b.blockDataString());
        }

        System.out.printf("\n===\nBlockchain: Verified? %b\n",chain.verifyChain());

    }
}