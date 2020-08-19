/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.arm;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UrlPathSegments {

    public static final String SEGMENT_NAME_EMPTY = "";

    public enum ParameterSegmentType {
        RESOURCE_GROUP,
        SUBSCRIPTION,
        OTHER
    }

    public interface Segment {
        String getSegmentName();

        boolean isParameterSegment();
    }

    public static class ParameterSegment implements Segment {
        private final String segmentName;
        private final String parameterName;
        private final ParameterSegmentType type;

        public ParameterSegment(String segmentName, String parameterName) {
            this.segmentName = segmentName;
            this.parameterName = parameterName;

            switch (segmentName) {
                case "resourceGroups":
                    this.type = ParameterSegmentType.RESOURCE_GROUP;
                    break;
                case "subscriptions":
                    this.type = ParameterSegmentType.SUBSCRIPTION;
                    break;
                default:
                    this.type = ParameterSegmentType.OTHER;
                    break;
            }
        }

        @Override
        public String getSegmentName() {
            return segmentName;
        }

        @Override
        public boolean isParameterSegment() {
            return true;
        }

        public String getParameterName() {
            return parameterName;
        }

        public ParameterSegmentType getType() {
            return type;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("Parameter: segName=").append(segmentName)
                    .append(", parameterName=").append(parameterName)
                    .append(", type=").append(type)
                    .toString();
        }
    }

    public static class LiteralSegment implements Segment {
        private final String segmentName;

        public LiteralSegment(String segmentName) {
            this.segmentName = segmentName;
        }

        @Override
        public String getSegmentName() {
            return segmentName;
        }

        @Override
        public boolean isParameterSegment() {
            return false;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("Literal: segName=").append(segmentName)
                    .toString();
        }
    }

    private final String path;

    private final List<Segment> reverseSegments = new ArrayList<>();

    public UrlPathSegments(String path) {
        this.path = path;

        String[] segmentArray = path.split(Pattern.quote("/"));

        String currentParameterName = null;
        for (int i = segmentArray.length - 1; i >= 0; --i) {
            String segmentStr = segmentArray[i].trim();

            if (!segmentStr.isEmpty()) {
                if (segmentStr.startsWith("{") && segmentStr.endsWith("}")) {
                    String parameterName = segmentStr.substring(1, segmentStr.length() - 1);

                    if (currentParameterName != null) {
                        reverseSegments.add(new ParameterSegment(SEGMENT_NAME_EMPTY, currentParameterName));
                        currentParameterName = parameterName;
                    } else {
                        currentParameterName = parameterName;
                    }
                } else {
                    String segmentName = segmentStr;

                    if (currentParameterName != null) {
                        reverseSegments.add(new ParameterSegment(segmentName, currentParameterName));
                        currentParameterName = null;
                    } else {
                        reverseSegments.add(new LiteralSegment(segmentName));
                    }
                }
            }
        }

        //Collections.reverse(segments);
    }

    public String getPath() {
        return path;
    }

    public List<Segment> getReverseSegments() {
        return reverseSegments;
    }

    public List<ParameterSegment> getReverseParameterSegments() {
        return reverseSegments.stream()
                .filter(Segment::isParameterSegment)
                .map(s -> (ParameterSegment) s)
                .collect(Collectors.toList());
    }

    public boolean hasResourceGroup() {
        return getNestLevel() >= 1 && reverseSegments.stream()
                .filter(Segment::isParameterSegment)
                .map(s -> (ParameterSegment) s)
                .anyMatch(s -> s.getType() == ParameterSegmentType.RESOURCE_GROUP);
    }

    public boolean hasSubscription() {
        return getNestLevel() >= 1 && reverseSegments.stream()
                .filter(Segment::isParameterSegment)
                .map(s -> (ParameterSegment) s)
                .anyMatch(s -> s.getType() == ParameterSegmentType.SUBSCRIPTION);
    }

    public boolean isNested() {
        return getNestLevel() > 1;
    }

    private int getNestLevel() {
        int level = 0;
        for (Segment segment : reverseSegments) {
            if (segment.isParameterSegment()) {
                ParameterSegmentType type = ((ParameterSegment) segment).getType();
                if (type == ParameterSegmentType.OTHER) {
                    level++;
                } else {
                    break;
                }
            }
        }
        return level;
    }

    @Override
    public String toString() {
        return reverseSegments.toString();
    }
}
