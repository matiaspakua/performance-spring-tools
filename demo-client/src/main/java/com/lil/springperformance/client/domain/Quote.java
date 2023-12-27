package com.lil.springperformance.client.domain;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

	private String _id;
	private String author;
	private String content;
	private ArrayList<String> tags;
	private String authorSlug;
	private int length;
	private Date dateAdded;
	private Date dateModified;

	public Quote() {
	}

	
	
	public String get_id() {
		return _id;
	}



	public void set_id(String _id) {
		this._id = _id;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public ArrayList<String> getTags() {
		return tags;
	}



	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}



	public String getAuthorSlug() {
		return authorSlug;
	}



	public void setAuthorSlug(String authorSlug) {
		this.authorSlug = authorSlug;
	}



	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}



	public Date getDateAdded() {
		return dateAdded;
	}



	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}



	public Date getDateModified() {
		return dateModified;
	}



	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}



	@Override
	public String toString() {
		return "Quote{" + "type='" + tags.toString() + '\'' + ", value=" + content + '}';
	}
}
