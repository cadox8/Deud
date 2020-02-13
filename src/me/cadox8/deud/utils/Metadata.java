package me.cadox8.deud.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Metadata {

    private final String metadataName;
    private final Object object;
}
