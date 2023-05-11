package com.piyal.graphql.bookDetails.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Author(UUID id, String firstName, String lastName) {}
