package com.piyal.graphql.bookDetails.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Book(UUID id, String name, int pageCount, Author author) {}
