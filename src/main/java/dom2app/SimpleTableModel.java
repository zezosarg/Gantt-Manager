package dom2app;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;

public class SimpleTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final String[] columnNames;
    private List<String[]> data;
    private String name;
    private String prjName;
    
    public SimpleTableModel(String name, String prjName, String[] pColumnNames, List<String[]> pData) {
    	this.name = name;
    	this.prjName = prjName;
    	this.columnNames = pColumnNames;
        this.data = pData;
        this.fireTableDataChanged();
    }
       
    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public int getRowCount() { return data.size(); }

    @Override
    public String getColumnName(int col) { return columnNames[col]; }

    @Override
    public Object getValueAt(int row, int col) { return data.get(row)[col]; }
    
	public List<String[]> getData() { return data; }

	public void setData(List<String[]> data) { this.data = data; }

	public String[] getColumnNames() { return columnNames; }

	public String getName() { return this.name; }

	public String getPrjName() { return this.prjName; }
	
	public String toString() {
		String result = this.name + "\tfor\t" + this.prjName + "\n";
		for(String s: this.columnNames)
			result += s.toString() + "\t";
		result +="\n";
		for(String[] ss: this.data) {
			for(String s: ss)
				result += s.toString() + "\t";
			result +="\n";
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(columnNames);
		result = prime * result + Objects.hash(data, name, prjName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleTableModel other = (SimpleTableModel) obj;
		return Arrays.equals(columnNames, other.columnNames) && equalsData(data, other.data)
				&& Objects.equals(name, other.name) && Objects.equals(prjName, other.prjName);
	}
	
	public boolean equalsData(List<String[]> data1, List<String[]> data2) {
		if (data1.size() != data2.size())return false;
		for (int i = 0; i < data1.size(); i++) {
			if (data1.get(i).length != data2.get(i).length) return false;
			for(int j = 0; j < data1.get(i).length; j++) {
				if (!data1.get(i)[j].equals(data2.get(i)[j])) return false;
			}	
		}
		return true;
	}
	
}//end class
