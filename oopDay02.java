import java.util.*;
import java.time.*;
class array{
	String Id;
	String name;
	String Pnumber;
	String Cname;
	String BDay;
	double salary;
	
	array(String Id,String name,String Pnumber,String Cname,String BDay,double salary){
		this.Id=Id;
		this.name=name;
		this.Pnumber=Pnumber;
		this.Cname=Cname;
		this.BDay=BDay;
		this.salary=salary;
	}
	
}
class mainArray{
    public array[]contact;
    int nextIndex;
    int size;
    int loadFactor;

    mainArray(int size,int loadFactor){
        contact=new array[size];
        nextIndex=0;
        this.size=size;
        this.loadFactor=loadFactor;
    }
    public void arrayExtend(){
        array[] temp=new array[contact.length+loadFactor];
        for(int i=0; i<nextIndex; i++){
            temp[i]=contact[i];
        }
        contact=temp;
    }
    private boolean isFull(){
		return nextIndex==contact.length;
	}
    public void pop(String Id,String name,String Pnumber,String Cname,String BDay,double salary){
		if(isFull()){
				arrayExtend();
				contact[nextIndex]=new array(Id,name, Pnumber,Cname,BDay,salary);
			nextIndex++;
		}
		else{
        contact[nextIndex]=new array(Id,name, Pnumber,Cname,BDay,salary);
        nextIndex++;
		}
    }
     public  int searchByNameOrPhoneNumber(String nameOrPhone){
        for(int i=0; i<nextIndex; i++){
            if(contact[i].name.equals(nameOrPhone) || contact[i].Pnumber.equals(nameOrPhone)){
                return i;
            }
        }
        return -1;
    }
    public  void printDetails(int index){
        System.out.println("Contact Id : "+contact[index].Id);
        System.out.println("Name : "+contact[index].name);
        System.out.println("Phone Number : "+contact[index].Pnumber);
        System.out.println("Companu Name : "+contact[index].Cname);
        System.out.println("Salary : "+contact[index].salary);
        System.out.println("Birthday : "+contact[index].BDay);
    }
    public void delete(int index){ 
		array[]tempary = new array[contact.length];

        for(int i=index; i<nextIndex-2; i++) {
			contact[i]=contact[i+1];
        }
        for(int i=0; i<contact.length-1; i++) {
			tempary[i]=contact[i];
        }
        contact=tempary;
        nextIndex--;
    }

}
class Work{
	public static mainArray arrayNew=new mainArray(100,50);
	
	public final static void clearConsole() { 
			try {
			final String os = System.getProperty("os.name"); 
			if (os.contains("Windows")) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
			System.out.print("\033[H\033[2J"); 
			System.out.flush();
			}
			} catch (final Exception e) {
			e.printStackTrace();
			// Handle any exceptions.
			}
		}
	
	public static void main(String args[]){
    homepage();
    }
    public static void homepage(){
        Scanner input = new Scanner(System.in);
        System.out.println("=======================iFRIEND CONTACT ORGANIZER============================");
        System.out.println("\n[01] Add Contacts");
        System.out.println("\n[02] Update Contacts");
        System.out.println("\n[03] Delete Contacts");
        System.out.println("\n[04] Search Contacts");
        System.out.println("\n[05] List Contacts");
        System.out.println("\n[06] Exit");
        System.out.print("\nEnter option to continue : ");
        int option=input.nextInt();
        clearConsole();
        switch(option){
            case 1 : addContacts();break;
            case 2 : updateContacts();break;
            case 3 : deleteContacts();break;
            case 4 : searchContacts();break;
            case 5 : listContacts();break;
            case 6 : exit();break;
            default : System.out.println("Invalid option...");break;
        }

    }
    public static String generateId(){
		if(arrayNew.nextIndex==0){
			return "C0001";
		}
		String lastId=arrayNew.contact[arrayNew.nextIndex-1].Id;
		int lastNo=Integer.parseInt(lastId.substring(1));
		return String.format("C%04d",lastNo+1);
	}
    
    public static boolean isValidPhoneNumber(String phoneNumber){
        if(phoneNumber.length()==10 && phoneNumber.charAt(0)=='0'){
            for(int i=1; i<phoneNumber.length(); i++){
                if(!Character.isDigit(phoneNumber.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        return false;

    }
    public static boolean isValidSalary(double salary){
        return salary>0;
    }

	public static boolean isValidBirthday(String birthday){
        String y=birthday.substring(0,4);
		int year=Integer.parseInt(y);
		String m=birthday.substring(5,7);
		int month=Integer.parseInt(m);
		String d=birthday.substring(8,10);
		int day=Integer.parseInt(d);
		LocalDate currentDate = LocalDate.now();
		int currentMonthValue = currentDate.getMonthValue();
		int currentYear=currentDate.getYear();    
		int currentMonthDate=currentDate.getDayOfMonth();
			
		if(year%4!=0 & month==2){
			if(day>28){
				return false;
			}else{
				return true;
			}
		}
		if(year%4==0 & month==2){
			if(day>29){
				return false;
			}else{
				return true;
			}
		}
		if(month==4 || month==6 || month==9 || month==11){
			if(day>30){
				return false;					
			}
		}
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			if(day>31){
				return false;
			}	
		}
		if(month>12){
			return false;
		}
		if(year<currentYear){
			return true;
			}else if(year==currentYear){
									
				if(month>currentMonthValue){
					return true;
				}else if(month==currentMonthValue){
									
					if(day<=currentMonthDate){
						return true;
					}
				}
			}
					return false;
    }

    public static void addContacts(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("======================ADD CONTACTS================");
            String id = generateId();
            System.out.println("\n"+id);
            System.out.println("=============");
            System.out.print("Name : ");
            String name=input.next();
            String phoneNumber;
            L1:do{
                System.out.print("Phone Number : ");
                phoneNumber  = input.next();
                if(!isValidPhoneNumber(phoneNumber)){
                    System.out.println("\n\tInvalid phone number...");
                    System.out.print("\nDo you want to input phone number again : ");
                    char ch = input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        System.out.print("\033[5A");
                        System.out.print("\033[0J");
                        continue L1;
                    }else if(ch=='N'||ch=='n'){
                        clearConsole();
                        homepage();
                    }
                }

            }while(!isValidPhoneNumber(phoneNumber));

            System.out.print("Company Name : ");
            String companyName  = input.next();
            double salary;

            L2:do{
                System.out.print("Salary : ");
                salary=input.nextDouble();
                if(!isValidSalary(salary)){
                    System.out.println("\n\tInvalid salary...");
                    System.out.print("\nDo you want to input salary again : ");
                    char ch=input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        System.out.print("\033[5A");
                        System.out.print("\033[0J");
                        continue L2;
                    }else if(ch=='N'||ch=='n'){
                        clearConsole();
                        homepage();
                    }
                }

            }while(!isValidSalary(salary));
            String birthday;

            L3:do{
                System.out.print("Birthday : ");
                birthday = input.next();
                if(!isValidBirthday(birthday)){
                    System.out.println("\n\tInvalid birthday...");
                    System.out.print("\nDo you want to input birthday again : ");
                    char ch=input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        System.out.print("\033[5A");
                        System.out.print("\033[0J");
                        continue L3;
                    }else if(ch=='N'||ch=='n'){
                        clearConsole();
                        homepage();
                    }
                }

            }while(!isValidBirthday(birthday));

           
            arrayNew.pop(id,name,phoneNumber,companyName,birthday,salary);

            System.out.println("\n\tContact has been added successfully...");
            System.out.print("\nDo you want to add another contact : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                addContacts();
            }else if(ch=='N'||ch=='n'){
                clearConsole();
                homepage();
            }

        }while(true);

    }
    
    public static void updateName(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Name");
        System.out.println("===================");
        System.out.print("\nInput new name : ");
        String newName = input.next();
        arrayNew.contact[index].name=newName;
    }
    //--------------------------UPDATE NAME----------------------------
    public static void updatePhoneNumber(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Phonenumber");
        System.out.println("========================");
        System.out.print("\nInput new phone number : ");
        String newPhoneNumber = input.next();
        arrayNew.contact[index].Pnumber=newPhoneNumber;
    }
    //--------------------------UPDATE NAME----------------------------
    public static void updateCompanyName(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Company Name");
        System.out.println("===========================");
        System.out.print("\nInput new company name : ");
        String newCompanyName = input.next();
        arrayNew.contact[index].Cname=newCompanyName;
    }
    //--------------------------UPDATE NAME----------------------------
    public static void updateSalary(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Salary");
        System.out.println("==================");
        System.out.print("\nInput new salary : ");
        double newSalary = input.nextDouble();
        arrayNew.contact[index].salary=newSalary;
    }
    
    public static void updateContacts(){
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("=======================UPDATE CONTACTS================");
            System.out.print("\nSearch contact by name or phone number : ");
            String nameOrPhone=input.next();
            int index = arrayNew.searchByNameOrPhoneNumber(nameOrPhone);

            if(index == -1){
                System.out.println("\n\tNo contact found for "+nameOrPhone);
                System.out.print("\nDo you want to try a new search : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    updateContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }
            else{
                arrayNew.printDetails(index);

                System.out.println("\nWhat do you want to update");
                System.out.println("\n\t[01] Name");
                System.out.println("\t[02] Phone number");
                System.out.println("\t[03] Company Name");
                System.out.println("\t[04] Salary");
                System.out.println("\nEnter an option to continue...");
                int option=input.nextInt();
                switch(option){
                    case 1 : updateName(index);break;
                    case 2 : updatePhoneNumber(index);break;
                    case 3 : updateCompanyName(index);break;
                    case 4 : updateSalary(index);break;
                    default : System.out.println("\n\tInvalid option...");
                }
                System.out.println("\nContact has been update successfully.");
                System.out.print("\nDo you want to update another contact : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    updateContacts();
                }else if(ch=='N'|| ch=='n'){
                    clearConsole();
                    homepage();
                }
            }
        }while(true);
    }
    
    public static void searchContacts(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("=====================SEARCH CONTACTS======================");
            System.out.print("\nSearch contact by name or phone number : ");
            String nameOrPhone=input.next();
            int index = arrayNew.searchByNameOrPhoneNumber(nameOrPhone);

            if(index == -1){
                System.out.println("\n\tNo contact found for "+nameOrPhone);
                System.out.print("\nDo you want to try a new search : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    searchContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }else{
                arrayNew.printDetails(index);
                System.out.print("\nDo you want to search another contact : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    searchContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }

        }while(true);
    }
    public static void deleteContacts(){
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("======================DELETE CONTACTS================");
            System.out.print("\nSearch contact by name or phone number : ");
            String nameOrPhone=input.next();
            int index = arrayNew.searchByNameOrPhoneNumber(nameOrPhone);

            if(index == -1){
                System.out.println("\n\tNo contact found for "+nameOrPhone);
                System.out.print("\nDo you want to try a new search : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    deleteContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }else{
                arrayNew.printDetails(index);
                L1:do{
                    System.out.print("\nDo you want to delete this contact : ");
                    char ch=input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        arrayNew.delete(index);
                        System.out.println("\nContact has been deleted successfully...");
                        break L1;
                    }else if(ch=='N'||ch=='n'){
                        break L1;
                    }

                }while(true);

                System.out.println("\nDo you want to delete another contact : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    deleteContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
                
            }

        }while(true);

    }
      public static void listContacts(){
        Scanner input = new Scanner(System.in);
        System.out.println("=======================SORT CONTACTS==========================");
        System.out.println("\n[01] Sorting by name");
        System.out.println("\n[02] Sorting by salary");
        System.out.println("\n[03] Sorting by birthday");
        System.out.print("\nEnter option to continue : ");
        int option=input.nextInt();
        switch(option){
            case 1 : sortByName();break;
            case 2 : sortBySalary();break;
            case 3 : sortByBirthday();break;
            default : System.out.println("\n\tInvalid option...");
        }

    }
    
     public static void sortByName(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("============LIST CONTACT BY NAME============\n");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|  Contact Id  |     Name     |   Phone Number   |    Company    |    Salary    |      Birthday     |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            sortingByName();

            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.print("\nDo you want to go homepage : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                homepage();
            }else if(ch=='N'|| ch=='n'){
                clearConsole();
                listContacts();
            }


        }while(true);
    }
    
    public static void sortingByName(){
		 array[] temp = new array[arrayNew.nextIndex];
   

        for(int i=0; i<arrayNew.nextIndex; i++){
			temp[i]=arrayNew.contact[i];

        }
        for(int j=1; j<arrayNew.nextIndex; j++){
            for(int i=0; i<arrayNew.nextIndex-j; i++){
                if(temp[i].name.compareTo(temp[i+1].name)>0){
                    array tempName=temp[i];
                    temp[i]=temp[i+1];
                    temp[i+1]=tempName;
                }
            }
        }

        for(int i=0; i<arrayNew.nextIndex; i++) {
            System.out.printf("| %-12s| %-12s| %-12s| %-12s| %-12.2f| %-12s|\n",temp[i].Id,temp[i].name,temp[i].Pnumber,temp[i].Cname,temp[i].salary,temp[i].BDay);
        }

    }
    
    public static void sortingBySalary(){
		 array[]temp=new array[arrayNew.nextIndex];

        for(int i=0; i<arrayNew.nextIndex; i++){
            temp[i]=arrayNew.contact[i];
           
        }
        for(int j=1; j<arrayNew.nextIndex; j++){
            for(int i=0; i<arrayNew.nextIndex-j; i++){
                if(temp[i].salary>temp[i+1].salary){
                    array tempName=temp[i];
                    temp[i]=temp[i+1];
                    temp[i+1]=tempName;
                }
            }
        }

        for(int i=0; i<arrayNew.nextIndex; i++) {
            System.out.printf("| %-12s| %-12s| %-12s| %-12s| %-12.2f| %-12s|\n",temp[i].Id,temp[i].name,temp[i].Pnumber,temp[i].Cname,temp[i].salary,temp[i].BDay);
        }

    }
    public static void sortBySalary(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("============LIST CONTACT BY NAME============\n");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|  Contact Id  |     Name     |   Phone Number   |    Company    |    Salary    |      Birthday     |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            sortingBySalary();

            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.print("\nDo you want to go homepage : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                homepage();
            }else if(ch=='N'|| ch=='n'){
                clearConsole();
                listContacts();
            }


        }while(true);
    }
    
    public static void sortingByBirthday(){
		array[]temp=new array[arrayNew.nextIndex];

        for(int i=0; i<arrayNew.nextIndex; i++){
            temp[i]=arrayNew.contact[i];
        }
        for(int j=1; j<arrayNew.nextIndex; j++){
            for(int i=0; i<arrayNew.nextIndex-j; i++){
                if(temp[i].BDay.compareTo(temp[i+1].BDay)>0){
                    array tempName=temp[i];
                    temp[i]=temp[i+1];
                    temp[i+1]=tempName;

                }
            }
        }

        for(int i=0; i<arrayNew.nextIndex; i++) {
            System.out.printf("| %-12s| %-12s| %-12s| %-12s| %-12.2f| %-12s|\n",temp[i].Id,temp[i].name,temp[i].Pnumber,temp[i].Cname,temp[i].salary,temp[i].BDay);
        }

    }
    
    public static void sortByBirthday(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("============LIST CONTACT BY NAME============\n");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|  Contact Id  |     Name     |   Phone Number   |    Company    |    Salary    |      Birthday     |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            sortingByBirthday();

            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.print("\nDo you want to go homepage : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                homepage();
            }else if(ch=='N'|| ch=='n'){
                clearConsole();
                listContacts();
            }


        }while(true);
    }
     public static void exit(){
        System.exit(0);
    }
}
