import javax.swing.JOptionPane;


/**
 * Created by Administrator on 2015/5/19.
 */
public class ChessControllor {
    private int chessColor = ChessModel.BLACK;
    private Boolean isStart = false;
    private boolean allow = false;
    
    public void setAllow(boolean allow) {
		this.allow = allow;
	}
    
    public void setChessColor(int chessColor) {
		this.chessColor = chessColor;
	}
    public void setStart(Boolean isStart) {
		this.isStart = isStart;
	}
 //�ؿ���Ϸ
    public void resetGame(){
    	for(int i=0;i<19;i++){
    		for(int j=0;j<19;j++)
    			FiveGame.model.resetChess(i, j);
    	}
    	FiveGame.view.repaint();
    }
    
    public void putChessInLocal(int row, int col) {
    	if(!isStart)return;
    	if(!allow)return;
        boolean success = FiveGame.model.putChess(row, col, chessColor);
        if (success) {	
            FiveGame.view.repaint();
            FiveGame.helper.sendChess(row, col);
            allow =false;
            //FiveGame.control.setStart(false);
            //chessColor = -chessColor;
            judge();
        }
    }


	public void putChessonline(int row,int col){
    	boolean success = FiveGame.model.putChess(row, col, -chessColor);
    	allow =true;
    	if(success){
    		FiveGame.view.repaint();
    		judge();
    	}
    }
	
    private void judge() {
		// TODO Auto-generated method stub
		int winner = FiveGame.model.whoWin();
		if(winner == ChessModel.BLACK){
			JOptionPane.showMessageDialog(null, "����ʤ");
			setStart(false);
		}else if(winner == ChessModel.WHITE){
			JOptionPane.showMessageDialog(null, "����ʤ");
			setStart(false);
		}
	}

	public void sendChatMessage(String text) {
		// TODO �Զ����ɵķ������
			FiveGame.helper.sendMessage("����://" + text);
			FiveGame.chatTextArea.append("����:"+text+"\n");
	}
	public void netChat(String msg) {
		FiveGame.chatTextArea.append("�Է�:"+msg);
		FiveGame.chatTextArea.append("\n");
	}

}
