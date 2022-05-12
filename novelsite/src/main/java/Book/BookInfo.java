package Book;

public class BookInfo {
	int BID;
	String BName;
	String Author;
	String Picture;
	int Votes;
	int Hits;
	int State;
	int WordCount;
	String Content;
	int CreateTime;
	int UpdateTime;

	public int getBID() {
		return BID;
	}

	public void setBID(int bID) {
		BID = bID;
	}

	public String getBName() {
		return BName;
	}

	public void setBName(String bName) {
		BName = bName;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public int getVotes() {
		return Votes;
	}

	public void setVotes(int votes) {
		Votes = votes;
	}

	public int getHits() {
		return Hits;
	}

	public void setHits(int hits) {
		Hits = hits;
	}

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}

	public int getWordCount() {
		return WordCount;
	}

	public void setWordCount(int wordcount) {
		this.WordCount = wordcount;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public int getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(int createtime) {
		CreateTime = createtime;
	}

	public int getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(int updatetime) {
		UpdateTime = updatetime;
	}

}
