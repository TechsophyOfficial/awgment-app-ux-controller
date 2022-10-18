package com.techsophy.tsf.wrapperservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTaskDTO
{
    String firstName;
    String lastName;
    String ticketNumder;
    String mobileNumber;
    String ticketDescription;
    String ticketType;
    String emailId;
    String createdOn;
    String status;

}
