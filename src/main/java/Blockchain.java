import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class Blockchain {
    private final static Logger LOGGER = Logger.getLogger(Blockchain.class.getName());

    private ArrayList<Block> chain = new ArrayList<Block>();

    public Blockchain() {

    }

    public boolean addBlock(Block block) {
        chain.add(block);
        return true;
    }

    public Block getBlock(Integer depth) {
        if (depth >= chain.size() || depth < 0) {
            throw new NoSuchElementException();
        }
        return chain.get(depth);
    }

    public Iterator<Block> iterator() {
        return new Iterator<Block>() {
            int currentIdx = chain.size() - 1;

            @Override
            public boolean hasNext() {
                return (currentIdx >= 0);
            }

            @Override
            public Block next() {
                return chain.get(currentIdx--);
            }
        };
    }

    public boolean verifyChain() {
        Iterator<Block> iter = chain.iterator();
        while (iter.hasNext()) {
            Block b = iter.next();
            if (b.verifyBlock() != true) return false;
        }
        return true;
    }
}
