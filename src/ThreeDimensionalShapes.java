/**
 * Filename: ThreeDimensionalShapes.java
 * Author: Joseph Julian
 * Date: 02 April 2023
 * Description: Contains arrays that depict 3D shapes (vertices, faces) and their respective colors (RGB).
 */
class ThreeDimensionalShapes {

    /**
     * Gray Surface
     */
    static final ThreeDimensionalShapes SURFACE =
            new ThreeDimensionalShapes(
                    new double[][]{
                            {-1, 0.1, 1},
                            {1, 0.1, 1},
                            {1, -0.1, 1},
                            {-1, -0.1, 1},
                            {1, 0.1, -1},
                            {1, -0.1, -1},
                            {-1, 0.1, -1},
                            {-1, -0.1, -1}
                    },
                    new int[][]{
                            {0, 1, 2, 3},
                            {1, 4, 5, 2},
                            {4, 6, 7, 5},
                            {6, 0, 3, 7},
                            {0, 1, 4, 6},
                            {3, 2, 5, 7}
                    },
                    new double[][]{
                            {0.5, 0.5, 0.5},
                            {0.5, 0.5, 0.5},
                            {0.5, 0.5, 0.5},
                            {0.5, 0.5, 0.5},
                            {0.5, 0.5, 0.5},
                            {0.5, 0.5, 0.5}
                    });

    /**
     * Red Octahedron
     */
    static final ThreeDimensionalShapes OCTAHEDRON =
            new ThreeDimensionalShapes(
                    new double[][]{
                            {0, 0.5, 0},
                            {0, -0.5, 0},
                            {0.5, 0, 0},
                            {-0.5, 0, 0},
                            {0, 0, 0.5},
                            {0, 0, -0.5},
                    },
                    new int[][]{
                            {0, 2, 4},
                            {0, 3, 4},
                            {0, 2, 5},
                            {0, 3, 5},
                            {1, 2, 4},
                            {1, 3, 4},
                            {1, 2, 5},
                            {1, 3, 5},
                    },
                    new double[][]{
                            {1.0, 0, 0},
                            {1.0, 0, 0},
                            {1.0, 0, 0},
                            {1.0, 0, 0},
                            {1.0, 0, 0},
                            {1.0, 0, 0},
                            {1.0, 0, 0},
                            {1.0, 0, 0},
                    });

    /**
     * Green Cube
     */
    static final ThreeDimensionalShapes CUBE =
            new ThreeDimensionalShapes(
                    new double[][]{
                            {-0.5, -0.5, -0.5},
                            {-0.5, -0.5, 0.5},
                            {-0.5, 0.5, -0.5},
                            {-0.5, 0.5, 0.5},
                            {0.5, -0.5, -0.5},
                            {0.5, -0.5, 0.5},
                            {0.5, 0.5, -0.5},
                            {0.5, 0.5, 0.5}
                    },
                    new int[][]{
                            {0, 1, 3, 2},
                            {0, 1, 5, 4},
                            {0, 2, 6, 4},
                            {1, 3, 7, 5},
                            {2, 3, 7, 6},
                            {4, 5, 7, 6}
                    },
                    new double[][]{
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0},
                            {0, 1.0, 0}
                    });

    /**
     * Blue Rectangular Prism
     */
    static final ThreeDimensionalShapes RECTANGULAR_PRISM =
            new ThreeDimensionalShapes(
                    new double[][]{
                            {-0.5, 0.375, 0.25},
                            {0.5, 0.375, 0.25},
                            {0.5, 0.375, -0.25},
                            {-0.5, 0.375, -0.25},
                            {-0.5, -0.375, 0.25},
                            {0.5, -0.375, 0.25},
                            {0.5, -0.375, -0.25},
                            {-0.5, -0.375, -0.25}
                    },
                    new int[][]{
                            {0, 1, 2, 3},
                            {4, 5, 6, 7},
                            {0, 1, 5, 4},
                            {1, 2, 6, 5},
                            {2, 3, 7, 6},
                            {3, 0, 4, 7}
                    },
                    new double[][]{
                            {0, 0, 1.0},
                            {0, 0, 1.0},
                            {0, 0, 1.0},
                            {0, 0, 1.0},
                            {0, 0, 1.0},
                            {0, 0, 1.0},
                            {0, 0, 1.0},
                            {0, 0, 1.0}
                    });

    /**
     * Yellow Tetrahedron
     */
    static final ThreeDimensionalShapes TETRAHEDRON =
            new ThreeDimensionalShapes(
                    new double[][]{
                            {-0.5, 0.5, 0.5},
                            {0.5, 0.5, 0.5},
                            {0, -0.5, 0.5},
                            {0, 0, -0.5},
                    },
                    new int[][]{
                            {0, 1, 2},
                            {0, 2, 3},
                            {1, 2, 3},
                            {0, 1, 3}
                    },
                    new double[][]{
                            {1.0, 1.0, 0},
                            {1.0, 1.0, 0},
                            {1.0, 1.0, 0},
                            {1.0, 1.0, 0}
                    });

    /**
     * Purple Pyramid
     */
    static final ThreeDimensionalShapes PYRAMID =
            new ThreeDimensionalShapes(
                    new double[][]{
                            {-0.5, 0, 0.5},
                            {0.5, 0, 0.5},
                            {0.5, 0, -0.5},
                            {-0.5, 0, -0.5},
                            {0, 0.75, 0},
                    },
                    new int[][]{
                            {0, 1, 4},
                            {1, 2, 4},
                            {2, 3, 4},
                            {3, 0, 4},
                            {1, 0, 3, 2},
                    },
                    new double[][]{
                            {1.0, 0, 1.0},
                            {1.0, 0, 1.0},
                            {1.0, 0, 1.0},
                            {1.0, 0, 1.0},
                            {1.0, 0, 1.0}
                    });

    double[][] vertices; // Vertex coordinates to construct a shape
    int[][] faces; // Faces that are made using coordinates from vertices array
    double[][] rgb; // RGB colors of the shape's faces

    /**
     * Default Constructor
     *
     * @param vertices Array of vertices that make up a shape
     * @param faces    Array of faces, using vertices that make up a shape
     * @param rgb      Array of color values for a shapes faces
     */
    private ThreeDimensionalShapes(double[][] vertices, int[][] faces, double[][] rgb) {
        this.vertices = vertices;
        this.faces = faces;
        this.rgb = rgb;
    }
}
