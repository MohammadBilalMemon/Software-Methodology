package SL.Util;
//Mohammad Bilal Memon(MBM186) and Jiya Kohli(JK1316)
public class SongItem {

		private String songName;
		private String artistName;
		private String album;
		private String year;
		
		public SongItem() {
			this.songName ="";
			this.artistName = "";
			this.album = "N/A"; // instead of keeping it blank to look nicer
			this.year = "N/A"; // // instead of keeping it blank to look nicer
		}
		
		public SongItem(String songName, String artistName, String album, String year) {
			this.songName = songName;
			this.artistName = artistName;
			this.album = album;
			this.year = year;
		}
		
		public String getName() {
			return songName;
		}
		
		public void setName(String songName) {
			this.songName = songName;
		}
		
		public String getArtist() {
			return artistName;
		}
		
		public void setArtist(String artistName) {
			this.artistName = artistName;
		}
		
		public String getYear() {
			return year;
		}
		
		public void setYear(String year) {
			this.year = year;
		}
		
		public void setAlbum(String album) {
			this.album = album;
		}
		
		public String getAlbum() {
			return album;
		}
}
