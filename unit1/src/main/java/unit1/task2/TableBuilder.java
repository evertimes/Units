package unit1.task2;

public class TableBuilder {

    private final StringBuilder data;
    private final String separatorTemplate;
    private final String lineTemplate;

    TableBuilder(int cellWidth) {
        this.data = new StringBuilder();
        this.separatorTemplate = "|%" + cellWidth + "s" + "|%" + cellWidth + "s" + "|\n";
        this.lineTemplate = ("+" + "-".repeat(cellWidth)).repeat(2) + "+\n";
    }

    public void addRow(Object firstValue, Object secondValue) {
        String row = String.format(separatorTemplate, firstValue, secondValue);
        data.append(row);

    }

    public void addRowSeparator() {
        data.append(lineTemplate);
    }

    public String build() {
        return data.toString();
    }
}

