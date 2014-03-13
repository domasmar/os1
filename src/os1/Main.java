package os1;

public class Main {

	public static void main(String args[]) {
		
		try { 
			CommandInterpreter ci = new CommandInterpreter("mov bx, 80");
			System.out.println(ci);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
	}
	
}
