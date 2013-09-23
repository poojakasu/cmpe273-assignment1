package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.domain.childReview;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(Include.NON_NULL)
public class ReviewDto extends LinksDto {
    //childReview review;
    @JsonProperty("Review") @NotEmpty
    private List<childReview> lstcReview;

    public ReviewDto(childReview review) {
	super();
	this.lstcReview = new ArrayList<childReview>();
	this.lstcReview.add(review);
    }

    public ReviewDto(List<childReview> lstReview) {
    	super();
    	this.lstcReview = new ArrayList<childReview>();
    	this.setlstcReview(lstReview);
        }

    /*public childReview getreview() {
	return review;
    }

   
    public void setreview(childReview review) {
	this.review = review;
    }
     */
    public List<childReview> getlstcReview()
    {
    return this.lstcReview;
    }
    public void setlstcReview(List<childReview> lstReview) {
		for(int i=0;i<lstReview.size();i++){
			this.lstcReview.add(lstReview.get(i));
    	//this.lstcReview = lstReview;
		}
	}
}
