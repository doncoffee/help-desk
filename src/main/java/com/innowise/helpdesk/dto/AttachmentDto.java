package com.innowise.helpdesk.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AttachmentDto {

    String name;
    byte[] blob;
}
