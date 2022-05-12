package Chapter;

public class ChapterInfo {
	int ChapterID;
	String ChapterName;
	public String getChapterName() {
		return ChapterName;
	}
	public void setChapterName(String chapterName) {
		ChapterName = chapterName;
	}
	int BID;
	String Part;
	int State;
	public int getChapterID() {
		return ChapterID;
	}
	public void setChapterID(int chapterID) {
		ChapterID = chapterID;
	}
	public int getBID() {
		return BID;
	}
	public void setBID(int bID) {
		BID = bID;
	}
	public String getPart() {
		return Part;
	}
	public void setPart(String part) {
		Part = part;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
}
