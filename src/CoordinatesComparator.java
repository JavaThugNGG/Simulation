import java.util.Comparator;

class CoordinatesComparator implements Comparator<Coordinates> {
    @Override
    public int compare(Coordinates c1, Coordinates c2) {
        if (c1.getRow() != c2.getRow()) {
            return Integer.compare(c1.getRow(), c2.getRow());
        }
        return Integer.compare(c1.getColumn(), c2.getColumn());
    }
}