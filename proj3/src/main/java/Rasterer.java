import java.util.HashMap;
import java.util.Map;


/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {
        // YOUR CODE HERE
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        Map<String, Object> results = new HashMap<>();
        String[][] renderGrid;
        int depth;
        double ullon, ullat, lrlon, lrlat, w;
        ullon = params.get("ullon");
        ullat = params.get("ullat");
        lrlon = params.get("lrlon");
        lrlat = params.get("lrlat");
        w = params.get("w");
        double startLon = MapServer.ROOT_ULLON;
        double startLat = MapServer.ROOT_ULLAT;
        double endLon = MapServer.ROOT_LRLON;
        double endLat = MapServer.ROOT_LRLAT;

        //find and put depth into result.
        depth = findDepth(lrlon, ullon, w);
        results.put("depth", depth);

        //create a render based on depth.
        int N = (int) Math.pow(2, depth);
        double lonGap = (endLon - startLon) / N;
        double latGap = (startLat - endLat) / N;

        int startLonBlock = sumBlock(ullon, startLon, lonGap);
        int startLatBlock = sumBlock(startLat, ullat, latGap);
        int endLonBlock = sumBlock(lrlon, startLon, lonGap) + 1;
        int endLatBlock = sumBlock(startLat, lrlat, latGap) + 1;

        renderGrid = mapCreator(startLonBlock, startLatBlock, endLonBlock, endLatBlock, depth);
        results.put("render_grid", renderGrid);

        //put other parameters.
        results.put("raster_ul_lon", startLon + startLonBlock * lonGap);
        results.put("raster_ul_lat", startLat - startLatBlock * latGap);
        results.put("raster_lr_lon", startLon + endLonBlock * lonGap);
        results.put("raster_lr_lat", startLat - endLatBlock * latGap);

        results.put("query_success", true);

        return results;
    }

    private int findDepth(double lrl, double ull, double width) {
        int result = 0;
        double lonDPP = (lrl - ull) / width;
        double start = 0.000171661376953125 * 2;
        while (lonDPP < start && result < 7) {
            result += 1;
            start /= 2;
        }
        return result;
    }

    private String[][] mapCreator(int a, int b, int c, int d, int e) {
        String[][] resultMap = new String[d - b][c - a];
        for (int i = 0; i < d - b; i++) {
            for (int j = 0; j < c - a; j++) {
                int x = a + j;
                int y = b + i;
                String pos = "d" + e + "_x" + x + "_y" + y + ".png";
                resultMap[i][j] = pos;
            }
        }
        return resultMap;
    }

    private int sumBlock(double a, double b, double diff) {
        return (int) Math.floor((a - b) / diff);
    }

}
