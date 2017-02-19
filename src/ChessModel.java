
/**
 * Created by Administrator on 2015/5/19.
 */
public class ChessModel {
	public static final int BLACK = -1;
	public static final int WHITE = 1;
	public static final int SPACE = 0;
	public static final int WIDTH = 19;

	private int[][] data = new int[WIDTH][WIDTH];
	private int lastRow;
	private int lastCol;
	private int lastColor;

	public boolean putChess(int row, int col, int color) {
		if (isAvailable(row) && isAvailable(col)
				&& (color == BLACK || color == WHITE)) {
			if (data[row][col] == SPACE) {
				data[row][col] = color;
				lastRow = row;
				lastCol = col;
				lastColor = color;
				return true;
			}
		}
		return false;
	}

	private boolean isAvailable(int rowOrColumn) {
		return rowOrColumn >= 0 && rowOrColumn < WIDTH;
	}
//��������
	public void resetChess(int row,int col){
		data[row][col]=SPACE;
	}
	public int[][] getChess() {
		int[][] result = new int[WIDTH][WIDTH];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = data[i][j];
			}
		}
		return result;
	}
//����
	public void rschess(){
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<WIDTH;j++)
				data[i][j]=0;
			FiveGame.view.repaint();
		}
	}
//�ж���Ӯ
	public int whoWin(){
    	//�������һ�������ж�ʤ��
    	int row ,col;
    	//���ж� ����
    	int countOfrow = 1;
    	int i = 1;
    	while(true){
    		row = lastRow - i;
    		col =lastCol;
    		
    		if(row < 0){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfrow++;
        		i++;
    		}else break;
    		//data[lastRow - i][lastCol]
    	}
    	//����
    	i=1;                  //��ʼ������
    	while(true){
    		row = lastRow + i;
    		col =lastCol;
    		
    		if(row > 18){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfrow++;
        		i++;
    		}else break;
    	}
    	
    	//���ж� ����
    	int countOfcol = 1;
    	i=1;
    	while(true){
    		row = lastRow;
    		col =lastCol - i;
    		
    		if(row < 0){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfcol++;
        		i++;
    		}else break;
    	}
    	
    	//����
    	i=1;
    	while(true){
    		row = lastRow;
    		col =lastCol + i;
    		
    		if(row > 18){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfcol++;
        		i++;
    		}else break;
    	}
    	
    	//  '\'����       backslash
    	int countOfbs =1;            
    	i=1;
    	while(true){
    		row = lastRow - i;
    		col =lastCol  - i;
    		
    		if(row < 0 || col<0){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfbs++;
        		i++;
    		}else break;
    	}
    	// ��\������
    	i=1;
    	while(true){
    		row = lastRow + i;
    		col =lastCol  + i;
    		
    		if(row > 18 || col > 18){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfbs++;
        		i++;
    		}else break;
    	}
    	// '/'����       slash
    	int countOfslash = 1;
    	i=1;
    	while(true){
    		row = lastRow - i;
    		col =lastCol  + i;
    		
    	//	if(row < 0 || col > 18){
    	//		break;
    	//	}
    		
    		if(data[row][col] == lastColor){
    			countOfslash++;
        		i++;
    		}else break;
    	}
    	//   '/'����          slash
    	i =1;
    	while(true){
    		row = lastRow + i;
    		col =lastCol  - i;
    		
    		if(row >18  || col < 0){
    			break;
    		}
    		if(data[row][col] == lastColor){
    			countOfslash++;
        		i++;
    		}else break;
    	}
    	
    	
    	
    	
    	
    	if(countOfrow >= 5 || countOfcol >=5 ||countOfbs >=5 || countOfslash >=5){
    		return lastColor;
    	}
    	
    	
        return 10;

    }
//����
	public void regretChess() {
		// TODO �Զ����ɵķ������
		data[lastRow][lastCol]=SPACE;
		FiveGame.view.repaint();
	}
}
