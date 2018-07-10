package SL.view;
//Mohammad Bilal Memon(MBM186) and Jiya Kohli(JK1316)

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Platform;
import SL.Util.SongItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.event.ActionEvent;

public class SongLibController {
	@FXML 
	ListView<SongItem> listView;    

	@FXML 
	private Button dB;

	@FXML 
	private Button aB;

	@FXML 
	private Button eB;
	
	@FXML
	private Button exitButton;
	
	@FXML 
	private TextField tSg;
	
	@FXML 
	private TextField tArt;
	
	@FXML
	private TextField tAlb;
	
	@FXML 
	private TextField tYr;
	
	@FXML
	private TextField songName;
	
	@FXML
	private TextField songArtist;
	
	@FXML
	private TextField songAlbum;
	
	@FXML
	private TextField songYear;
	
	
	
	
	
	private ObservableList<SongItem> obsList;
	private int num;
	int index;
	
	public static Comparator<SongItem> SongItemComparator = new Comparator<SongItem>() {
		public int compare(SongItem s1, SongItem s2) {
			if(s1.getName().compareToIgnoreCase(s2.getName())==0) {
				return s1.getArtist().compareToIgnoreCase(s2.getArtist());
			}else {
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		}

	};
	
	public void isContained(String name, String artist) {
		   obsList.forEach(
				   Song 
				   -> {
					   
					 if(name.equals(Song.getName().toLowerCase()) && artist.equals(Song.getArtist().toLowerCase())) {
						 throw new java.lang.Error();}});
		   
	   }
	
	
	public void detailsunEditable()
	{
		tSg.setDisable(true);
		tArt.setDisable(true);
		tAlb.setDisable(true);
		tYr.setDisable(true);
	}
	
	public void detailsEditable()
	{
		songName.setDisable(false);
		songArtist.setDisable(false);
		songAlbum.setDisable(false);
		songYear.setDisable(false);
	}
	
	
	
	
	public void onClick_editButton(ActionEvent e) throws IOException
	{
		if(listView.getSelectionModel().getSelectedItem()==null) {
			errDialog("Nothing Selected To Edit");
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
		
		
		
		SongItem item = listView.getSelectionModel().getSelectedItem();
		SongLibController.delete(item);
		obsList.remove(item);
		
		   String songname = songName.getText();
		   String songartist = songArtist.getText();
		   String Album = songAlbum.getText();
		   String Year = songYear.getText();
		   SongItem song = null;		   
		   if((songname != null) && (songartist != null) && (Album != null) && (Year != null)){
		   
		   song = new SongItem(songname, songartist, Album, Year);
		   }
		   
		   
		   for(SongItem i : obsList){
			   if(i != listView.getSelectionModel().getSelectedItem()){
			   
				   return;
			   }
		   }
		   
		   
		   if(song != null && !obsList.contains(song))
		   {
		   if (songname != null && !songname.trim().isEmpty())
		   {
			   item.setName(song.getName()); 
		   }
		   
		   
		   
		   if (songartist != null && !songartist.trim().isEmpty())
		   {
			   item.setArtist(song.getArtist());
			   
			   
			   try {
					 isContained(song.getName().trim().toLowerCase(),song.getArtist().trim().toLowerCase());
				 } catch (Error err) {
					 errDialog("Song by Artist Existss");
					 return;
	}
			   
		   }

		   if (Album != null)
		   {
			   if(songAlbum.getText().trim().equals(""))
				 {
					 item.setAlbum("Nothing Entered");
				 }
			   item.setAlbum(song.getAlbum());
			   }
		   if (Year != null)
		   {
			   if(songYear.getText().trim().equals(""))
				 {
					 item.setYear("Nothing Entered");
				 }
			   item.setYear(song.getYear());
			   }
		   }
		   else{
			   
			   return;
		   }
		   
		   
	
		   listView.setItems(obsList);   
		   
		 
		  obsList.add(item);
		   SongLibController.add(item);
		   listView.getSelectionModel().select(item);
		   obsList.sort(SongItemComparator);
return;
			}
		else if (result.get() == ButtonType.CANCEL)
		{
			return;
		}
			
		   
		   
		}
			
	
	
	public void onClick_addButton(ActionEvent e) throws IOException
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			 SongItem add = new SongItem();
			 
			 if(songName.getText().isEmpty() | songArtist.getText().isEmpty())
			 {
				 errDialog(" Please, at minimum, include the name and artist");
				 return;
			 }
			 
			 
			 if(songName.getText().isEmpty() && songArtist.getText().isEmpty() && songAlbum.getText().isEmpty() && songYear.getText().isEmpty())
			 {
				 errDialog("All fields are empty. Please, at minimum, include the name and artist");
				 return;
			 }
			 
			 
			 
			 
			 
			 
			 if(!songName.getText().isEmpty()){
				 add.setName(songName.getText().trim());
			 }
			 
			 if(!songArtist.getText().isEmpty()){
				 add.setArtist(songArtist.getText().trim());
				 try {
					 isContained(songName.getText().trim().toLowerCase(),songArtist.getText().trim().toLowerCase());
				 } catch (Error err) {
					 errDialog("Song by Artist Exists");
					 return;
	}
			 }		
			 
			 if(!songAlbum.getText().isEmpty()){
				 if(songAlbum.getText().trim().equals(""))
				 {
					 add.setAlbum("Nothing Entered");
				 }
					 
				 add.setAlbum(songAlbum.getText().trim());
				
			 }	
			 
			 if(!songYear.getText().isEmpty()){
				 if(songYear.getText().trim().equals(""))
				 {
					 add.setYear("Nothing Entered");
				 }
				 add.setYear(songYear.getText().trim());
			 }	
			 
 
 obsList.add(add);
 SongLibController.add(add);
 listView.getSelectionModel().select(add);
 obsList.sort(SongItemComparator);
 return;

		}
		else if (result.get() == ButtonType.OK)
		{
			return;
		}
		 }

	
	

	
	public void start(Stage mainStage) { 
		
		
			detailsunEditable();
			//detailsEditable();
			
			obsList = FXCollections.observableArrayList();
			listView.setItems(obsList);  
			
	      
	      
			listView.getSelectionModel().select(0);
			//listView.getSelectionModel().selectFirst();
			
	      
			ArrayList<SongItem> preList = SongLibController.init();
			
		
			
			while (index<preList.size()) {
				
				obsList.add(preList.get(index));
				index++;
				
				if(preList.size() == 0)
				{
					System.out.println("empty but continuing");
				}
			}
	     
			FXCollections.sort(obsList, SongItemComparator);
	      
			listView
	      	.getSelectionModel()
	      		.selectedIndexProperty()
	      			.addListener(
	           (obs, oldVal, newVal) -> 
	               num = listView.getSelectionModel().getSelectedIndex());
	      
			listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SongItem>() {

	    	    @Override
	    	    public void changed(ObservableValue<? extends SongItem> observable, SongItem oldValue, SongItem newValue) {
	    	    	if(newValue!=null) {
	    	    		tSg.setText("Song Name: \t"+"\""+newValue.getName()+"\"");
	    	    		tArt.setText("Artist: \t"+newValue.getArtist());
	    	    		tAlb.setText("Album: \t"+newValue.getAlbum());
	    	    		tYr.setText("Year: \t"+newValue.getYear());
	    	    		
	    	    		
	    	    		
	    	    	}
	    	    }
	    	});
	      
	      
	           		
	               
	      
			listView.setCellFactory(new Callback<ListView<SongItem>, ListCell<SongItem>>(){
	    	  
	            @Override
	            public ListCell<SongItem> call(ListView<SongItem> p) {
	                 
	                ListCell<SongItem> cell = new ListCell<SongItem>(){
	 
	                    @Override
	                    protected void updateItem(SongItem s, boolean bln) {
	                        super.updateItem(s, bln);
	                        if (s != null) {
	                            setText("\""+s.getName()+"\" by "+s.getArtist());
	                            tSg.setText("Song Name: \t"+"\""+s.getName()+"\"");
	                            tArt.setText("Artist: \t"+s.getArtist());
	                            tAlb.setText("Album: \t"+s.getAlbum());
	                            tYr.setText("Year: \t"+s.getYear());
	                           
	                        }
	                        else {
	                        	setText(null);
	                        }
	                    }
	 
	                };
	                 
	                return cell;
	            }
	        });
	      
			listView.getSelectionModel().select(0);

	   }	 
	
	
	
	
	
	public void onClick_deleteButton(ActionEvent e) throws IOException
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			
			SongLibController.delete(obsList.get(num));
			obsList.remove(num);
		}
		else if (result.get() == ButtonType.CANCEL) {
		return;	
		}
		
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	   
	  
	   public void ListEdit(ActionEvent e) throws IOException {
		   
		   
			
	   }
	   
	   
	   public static ArrayList<SongItem> init() {
			ArrayList<SongItem> preList= new ArrayList<SongItem>();
			File f = new File("SongLib.txt");
			if(!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try (Scanner scanner = new Scanner(f)) {

		        while (scanner.hasNextLine()) {
		        	String s = scanner.nextLine();
		           // System.out.println(s);
		            
		            String[] split = s.split(",");
		        //    System.out.println(split[1]);
		   
		            SongItem temp = new SongItem();
		          
		            	temp.setName(split[0]);
		            	temp.setArtist(split[1]);
		            	temp.setAlbum(split[2]);
		            	temp.setYear(split[3]);
		        
		            
		            preList.add(temp);
		            
		        
		        } 
		        scanner.close();

		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		 
			
			
			return preList;
			
		}
		
		
		
		
	   public void onCLick_exitButton(ActionEvent e)
		{
			Platform.exit();
		}
	   
	   
	 

		public static void add(SongItem temp) {
			FileWriter fw=null;
			try {
				fw = new FileWriter("SongLib.txt",true);
			} catch (IOException e) {
				
				System.out.println("Error in creating fileWriter/n/n/n/n");
				e.printStackTrace();
			}
			 try {
				fw.write(temp.getName()+","+temp.getArtist()+","+temp.getAlbum()+","+temp.getYear()+"\n");
				fw.close();
			} catch (IOException e) {
				System.out.println("Error in writing/n/n/n/n");
				e.printStackTrace();
			}
			
		
		}

		
		public static void delete(SongItem temp) throws IOException {
			File inputFile = new File("SongLib.txt");
	        File tempFile = new File("myTempFile.txt");

	        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

	        String lineToRemove = temp.getName()+","+temp.getArtist()+","+temp.getAlbum()+","+temp.getYear();
	        String currentLine;

	        while((currentLine = reader.readLine()) != null) {
	        	 String trimmedLine = currentLine.trim();
	 
	            if(trimmedLine.equals(lineToRemove)) continue;
	            writer.write(currentLine + System.getProperty("line.separator"));
	        }
	        writer.close(); 
	        reader.close(); 
	        if (inputFile.delete()) {
	        	boolean successful = tempFile.renameTo(inputFile);
	        }
	    
		}
		
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
			
	  
	   public void errDialog(String emessage) {
		   Alert alert = new Alert(AlertType.ERROR);
			//alert.initOwner(SongLib.getPrimaryStage());
			alert.setTitle("ALERT");
			alert.setHeaderText("ERROR");
			alert.setContentText(emessage);
			alert.showAndWait();
	   }
	   
	   

	
}
