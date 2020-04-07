package br.com.erudio.data.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","author", "lauch_date", "price", "title"})
public class BookVO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	@Mapping("id")
	@JsonProperty("id")
	private long key;
	
	private String author;
	
	private Date lauchDate;
	
	private Double price;
	
	private String title;

	public BookVO() {

	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLauchDate() {
		return lauchDate;
	}

	public void setLauchDate(Date lauchDate) {
		this.lauchDate = lauchDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + (int) (key ^ (key >>> 32));
		result = prime * result + ((lauchDate == null) ? 0 : lauchDate.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (key != other.key)
			return false;
		if (lauchDate == null) {
			if (other.lauchDate != null)
				return false;
		} else if (!lauchDate.equals(other.lauchDate))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


}
