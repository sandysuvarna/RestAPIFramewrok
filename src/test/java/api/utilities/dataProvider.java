package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProvider {

  @DataProvider(name="Data")
  public String[][] getAllData() throws IOException
  {
	  String path = System.getProperty("user.dir")+"//testData//usersList.xlsx";
	  XLUtility xl = new XLUtility();
	  
	  int rowNumber = xl.getRowCount("Sheet1", path);
	  int colNum = xl.getCellCount("Sheet1",path, 1);
	  String apidata[][] = new String[rowNumber][colNum];
	  for(int i=1;i<=rowNumber;i++)
	  {
		  for(int j=0;j<colNum;j++)
		  {
			  apidata[i-1][j]=xl.getCellData("Sheet1",i,j);
		  }
	  }
	  
	  return apidata;
	  
  }
  @DataProvider(name = Usernames)
  public String[] getUsernames()
  {
	  String path=System.getProperty("user.dir")+"//testData//usersList.xlsx";
	  XLUtility xl = new XLUtility(path);
	  
	  int rowNumber=xl.getRowCount(path,"Sheet1");
	  String apidata[] = new String[rowNumber];
	  
	  for(int i=1;i<=rowNumber;i++)
	  {
		  apidata[i-1][j]=xl.getCellData(path,"Sheet1",i,1);
	  }
       return apidata;
  
  
  }





}
