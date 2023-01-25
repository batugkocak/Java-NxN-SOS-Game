public class Step {
    public Step(int row, int column, String character) {
        this.row = row;
        this.column = column;
        this.character = character;
    }

    private int row;
    private int column;
    private String character;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

}
