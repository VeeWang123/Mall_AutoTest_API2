package apitest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.github.checkpoint.JsonPath;
import com.github.crab2died.ExcelUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianweiwang on 2020/2/4.
 */
public class JsonPathTest {

      public static void main(String[] args){
          String jstr="{\n" +
                  "  \"status\": \"OK\",\n" +
                  "  \"code\": 200,\n" +
                  "  \"data\": {\n" +
                  "    \"brandList\": [\n" +
                  "      {\n" +
                  "        \"brandId\": \"9c36b28099f32facc43ee994b2202974\",\n" +
                  "        \"brandName\": \"东方聚能\",\n" +
                  "        \"firstLetter\": \"D\",\n" +
                  "        \"score\": 12\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"brandId\": \"38676c1c4b256b0005347e63e857322b\",\n" +
                  "        \"brandName\": \"威尔达\",\n" +
                  "        \"firstLetter\": \"W\",\n" +
                  "        \"score\": 6\n" +
                  "      }\n" +
                  "    ],\n" +
                  "    \"crumbs\": {\n" +
                  "      \"levelOne\": {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200744506801\",\n" +
                  "        \"categoryName\": \"建筑工程\",\n" +
                  "        \"categoryLevel\": \"1\"\n" +
                  "      },\n" +
                  "      \"levelTwo\": {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200749312785\",\n" +
                  "        \"categoryName\": \"保温隔音材料\",\n" +
                  "        \"categoryLevel\": \"2\"\n" +
                  "      },\n" +
                  "      \"levelThree\": {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"categoryName\": \"一体板\",\n" +
                  "        \"categoryLevel\": \"3\"\n" +
                  "      },\n" +
                  "      \"located\": true\n" +
                  "    },\n" +
                  "    \"recommendation\": null,\n" +
                  "    \"category\": null,\n" +
                  "    \"parameters\": [\n" +
                  "      {\n" +
                  "        \"aggId\": null,\n" +
                  "        \"categoryId\": null,\n" +
                  "        \"specFieldId\": null,\n" +
                  "        \"specName\": \"产品结构\",\n" +
                  "        \"displayName\": \"产品结构\",\n" +
                  "        \"priceFlag\": \"N\",\n" +
                  "        \"orderSort\": \"2\",\n" +
                  "        \"specValues\": [\n" +
                  "          \"A型双层夹心复合结构\",\n" +
                  "          \"B型双层复合结构\",\n" +
                  "          \"AF型四折边复合结构\"\n" +
                  "        ]\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"aggId\": null,\n" +
                  "        \"categoryId\": null,\n" +
                  "        \"specFieldId\": null,\n" +
                  "        \"specName\": \"规格\",\n" +
                  "        \"displayName\": \"规格\",\n" +
                  "        \"priceFlag\": \"N\",\n" +
                  "        \"orderSort\": \"2\",\n" +
                  "        \"specValues\": [\n" +
                  "          \"1220*2440\"\n" +
                  "        ]\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"aggId\": null,\n" +
                  "        \"categoryId\": null,\n" +
                  "        \"specFieldId\": null,\n" +
                  "        \"specName\": \"面板材质\",\n" +
                  "        \"displayName\": \"面板材质\",\n" +
                  "        \"priceFlag\": \"Y\",\n" +
                  "        \"orderSort\": \"3\",\n" +
                  "        \"specValues\": [\n" +
                  "          \"天然石材\",\n" +
                  "          \"镀铝锌板\",\n" +
                  "          \"无机树脂+纤维水泥\"\n" +
                  "        ]\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"aggId\": null,\n" +
                  "        \"categoryId\": null,\n" +
                  "        \"specFieldId\": null,\n" +
                  "        \"specName\": \"面板厚度\",\n" +
                  "        \"displayName\": \"面板厚度\",\n" +
                  "        \"priceFlag\": \"Y\",\n" +
                  "        \"orderSort\": \"3\",\n" +
                  "        \"specValues\": [\n" +
                  "          \"6mm—8mm\",\n" +
                  "          \"6mm\",\n" +
                  "          \"8mm\",\n" +
                  "          \"10mm\",\n" +
                  "          \"0.5—0.6mm\"\n" +
                  "        ]\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"aggId\": null,\n" +
                  "        \"categoryId\": null,\n" +
                  "        \"specFieldId\": null,\n" +
                  "        \"specName\": \"保温层厚度\",\n" +
                  "        \"displayName\": \"保温层厚度\",\n" +
                  "        \"priceFlag\": \"Y\",\n" +
                  "        \"orderSort\": \"5\",\n" +
                  "        \"specValues\": [\n" +
                  "          \"20-80mm\"\n" +
                  "        ]\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"aggId\": null,\n" +
                  "        \"categoryId\": null,\n" +
                  "        \"specFieldId\": null,\n" +
                  "        \"specName\": \"产品密度\",\n" +
                  "        \"displayName\": \"产品密度\",\n" +
                  "        \"priceFlag\": \"N\",\n" +
                  "        \"orderSort\": \"6\",\n" +
                  "        \"specValues\": [\n" +
                  "          \"20—30kg/㎡\",\n" +
                  "          \"<20kg/㎡\",\n" +
                  "          \"12—15kg/㎡\",\n" +
                  "          \"10—15kg/㎡\"\n" +
                  "        ]\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"aggId\": null,\n" +
                  "        \"categoryId\": null,\n" +
                  "        \"specFieldId\": null,\n" +
                  "        \"specName\": \"抗拉强度\",\n" +
                  "        \"displayName\": \"抗拉强度\",\n" +
                  "        \"priceFlag\": \"N\",\n" +
                  "        \"orderSort\": \"7\",\n" +
                  "        \"specValues\": [\n" +
                  "          \"0.1—0.15Mpa\",\n" +
                  "          \"0.15—0.25Mpa\"\n" +
                  "        ]\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"aggId\": null,\n" +
                  "        \"categoryId\": null,\n" +
                  "        \"specFieldId\": null,\n" +
                  "        \"specName\": \"阻燃等级\",\n" +
                  "        \"displayName\": \"阻燃等级\",\n" +
                  "        \"priceFlag\": \"N\",\n" +
                  "        \"orderSort\": \"8\",\n" +
                  "        \"specValues\": [\n" +
                  "          \"A级\",\n" +
                  "          \"有机板B1级，无机板A级\"\n" +
                  "        ]\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"aggId\": null,\n" +
                  "        \"categoryId\": null,\n" +
                  "        \"specFieldId\": null,\n" +
                  "        \"specName\": \"安装方式\",\n" +
                  "        \"displayName\": \"安装方式\",\n" +
                  "        \"priceFlag\": \"N\",\n" +
                  "        \"orderSort\": \"9\",\n" +
                  "        \"specValues\": [\n" +
                  "          \"锚固\",\n" +
                  "          \"粘贴+锚固\",\n" +
                  "          \"粘贴+锚固或龙骨干挂\"\n" +
                  "        ]\n" +
                  "      }\n" +
                  "    ],\n" +
                  "    \"itemList\": [\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"6777857490028378dcef6ed78a8939d6\",\n" +
                  "        \"itemCode\": \"10003194\",\n" +
                  "        \"itemName\": \"氟碳金属面层8mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 90.00,\n" +
                  "        \"maxPrice\": 90.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"dc9d2ecc62fc6a5f757f1679ee705ae2\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"8mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M01/00/75/rBCwJFta6U2AF3ltAAUpwVrhODY742.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"4561d99f0f2f9bd25505787d7a20983e\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"2c67df2bec54e62a15a3df9a9775f037\",\n" +
                  "        \"itemCode\": \"10003207\",\n" +
                  "        \"itemName\": \"【测试】东方聚能 质感仿石材（水包砂）10mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 114.00,\n" +
                  "        \"maxPrice\": 114.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"470af0d29ee988097cc193e6bd2d2d76\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"10mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M01/00/77/rBCwI1ta7pSAJpsyAArMwx09LRI203.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"0943689f48c773f48e6fefe959969009\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"e42f94a4ea66d8faf74bb165029352cf\",\n" +
                  "        \"itemCode\": \"10004377\",\n" +
                  "        \"itemName\": \"威尔达氟碳漆饰面系列-实色漆\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"m2\",\n" +
                  "        \"unitName\": \"平方米\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": -1.00,\n" +
                  "        \"maxPrice\": -1.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"22017738be4853dd742e6c4864bad0da\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"威尔达\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品结构\",\n" +
                  "            \"value\": \"A型双层夹心复合结构\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板材质\",\n" +
                  "            \"value\": \"无机树脂+纤维水泥\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"6mm—8mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"4\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"保温层厚度\",\n" +
                  "            \"value\": \"20-80mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"5\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品密度\",\n" +
                  "            \"value\": \"20—30kg/㎡\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"6\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"抗拉强度\",\n" +
                  "            \"value\": \"0.15—0.25Mpa\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"7\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"阻燃等级\",\n" +
                  "            \"value\": \"有机板B1级，无机板A级\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"8\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"安装方式\",\n" +
                  "            \"value\": \"粘贴+锚固或龙骨干挂\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"9\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M01/01/64/rBCwI1vP4K2AHdDnAAAetWSWujY056.jpg\",\n" +
                  "        \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "        \"spuId\": \"e2d2a8c0ed3ee0e087d73b67c52e8bb5\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"威尔达\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"90ff28ec5c449fb51437b950bec4e2e6\",\n" +
                  "        \"itemCode\": \"10003200\",\n" +
                  "        \"itemName\": \"质感仿石材（水包砂）6mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 104.00,\n" +
                  "        \"maxPrice\": 104.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"e83e5e908f4a5528ea04cdd5860a1e5a\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"6mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M00/00/76/rBCwI1ta7TyAVogLAArMwx09LRI219.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"0943689f48c773f48e6fefe959969009\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"21b5f25839d932db992e2678836fc808\",\n" +
                  "        \"itemCode\": \"10004370\",\n" +
                  "        \"itemName\": \"威尔达 真石饰面系列\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"m2\",\n" +
                  "        \"unitName\": \"平方米\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": -1.00,\n" +
                  "        \"maxPrice\": -1.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"f3f4c8dfdb5e6f3a1e3529e5e231908d\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"威尔达\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品结构\",\n" +
                  "            \"value\": \"B型双层复合结构\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板材质\",\n" +
                  "            \"value\": \"天然石材\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"6mm—8mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"4\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"保温层厚度\",\n" +
                  "            \"value\": \"20-80mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"5\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品密度\",\n" +
                  "            \"value\": \"12—15kg/㎡\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"6\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"抗拉强度\",\n" +
                  "            \"value\": \"0.1—0.15Mpa\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"7\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"阻燃等级\",\n" +
                  "            \"value\": \"A级\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"8\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"安装方式\",\n" +
                  "            \"value\": \"锚固\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"9\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M00/01/61/rBCwI1vO1qGAez0aAABCuIqzhNY188.jpg\",\n" +
                  "        \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "        \"spuId\": \"e2d2a8c0ed3ee0e087d73b67c52e8bb5\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"威尔达\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"4c951e51b842e5e9ca4b3fc8f65bb5ff\",\n" +
                  "        \"itemCode\": \"10003197\",\n" +
                  "        \"itemName\": \"多彩（水包水、真石漆）面层8mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 106.00,\n" +
                  "        \"maxPrice\": 106.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"20d4df133d9b878faeb4748926cdbffd\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"8mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M00/00/76/rBCwI1ta67CASHuWAAhaoMH0W5o928.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"103678e7fae78ef18e47c89a34d20a3d\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"17791a24cf3aaf1adb6312342a52ee0e\",\n" +
                  "        \"itemCode\": \"10003198\",\n" +
                  "        \"itemName\": \"多彩（水包水、真石漆）面层10mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 112.00,\n" +
                  "        \"maxPrice\": 112.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"05a00daba2607b0e2db5965a1075d3e0\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"10mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M01/00/76/rBCwI1ta7DSARCr_AAhaoMH0W5o340.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"103678e7fae78ef18e47c89a34d20a3d\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"5d0a706ca8046a8fb8a02ae84850564e\",\n" +
                  "        \"itemCode\": \"10004375\",\n" +
                  "        \"itemName\": \"威尔达丽彩漆饰面系列\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"m2\",\n" +
                  "        \"unitName\": \"平方米\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": -1.00,\n" +
                  "        \"maxPrice\": -1.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"4bbd776236acc8d7ef98f2dcc9887191\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"威尔达\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品结构\",\n" +
                  "            \"value\": \"A型双层夹心复合结构\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板材质\",\n" +
                  "            \"value\": \"无机树脂+纤维水泥\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"6mm—8mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"4\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"保温层厚度\",\n" +
                  "            \"value\": \"20-80mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"5\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品密度\",\n" +
                  "            \"value\": \"<20kg/㎡\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"6\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"抗拉强度\",\n" +
                  "            \"value\": \"0.15—0.25Mpa\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"7\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"阻燃等级\",\n" +
                  "            \"value\": \"有机板B1级，无机板A级\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"8\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"安装方式\",\n" +
                  "            \"value\": \"粘贴+锚固或龙骨干挂\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"9\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M01/01/63/rBCwI1vP1NOAV5LDAAAkw3hJXfw843.jpg\",\n" +
                  "        \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "        \"spuId\": \"e2d2a8c0ed3ee0e087d73b67c52e8bb5\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"威尔达\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"f24f4c6ed33d95e2f7ad6182dd04d7c4\",\n" +
                  "        \"itemCode\": \"10004380\",\n" +
                  "        \"itemName\": \"威尔达金属板饰面系列-金属漆\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"m2\",\n" +
                  "        \"unitName\": \"平方米\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": -1.00,\n" +
                  "        \"maxPrice\": -1.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"fa330e5164648566ddd00c55e2a246f0\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"威尔达\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品结构\",\n" +
                  "            \"value\": \"AF型四折边复合结构\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板材质\",\n" +
                  "            \"value\": \"镀铝锌板\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"0.5—0.6mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"4\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"保温层厚度\",\n" +
                  "            \"value\": \"20-80mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"5\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品密度\",\n" +
                  "            \"value\": \"10—15kg/㎡\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"6\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"抗拉强度\",\n" +
                  "            \"value\": \"0.1—0.15Mpa\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"7\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"阻燃等级\",\n" +
                  "            \"value\": \"有机板B1级，无机板A级\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"8\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"安装方式\",\n" +
                  "            \"value\": \"粘贴+锚固或龙骨干挂\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"9\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M00/01/65/rBCwI1vP_1KAGHLpAABU88YgQL0444.jpg\",\n" +
                  "        \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "        \"spuId\": \"e2d2a8c0ed3ee0e087d73b67c52e8bb5\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"威尔达\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"1996e4f63173cce77dadd10a74d7a289\",\n" +
                  "        \"itemCode\": \"10003193\",\n" +
                  "        \"itemName\": \"氟碳金属面层6mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 86.00,\n" +
                  "        \"maxPrice\": 86.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"e5420ffc58449aa559709deb804d2a28\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"6mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M01/00/75/rBCwI1ta6KqATK-XAAUpwVrhODY718.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"4561d99f0f2f9bd25505787d7a20983e\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"de45b2886e8c5767c29bc4e0f9436675\",\n" +
                  "        \"itemCode\": \"10004374\",\n" +
                  "        \"itemName\": \"威尔达彩涂装饰面系列\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"m2\",\n" +
                  "        \"unitName\": \"平方米\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": -1.00,\n" +
                  "        \"maxPrice\": -1.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"c55ad2e62c9443eeea4297ca784dabfa\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"威尔达\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品结构\",\n" +
                  "            \"value\": \"B型双层复合结构\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板材质\",\n" +
                  "            \"value\": \"无机树脂+纤维水泥\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"6mm—8mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"4\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"保温层厚度\",\n" +
                  "            \"value\": \"20-80mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"5\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"产品密度\",\n" +
                  "            \"value\": \"12—15kg/㎡\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"6\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"抗拉强度\",\n" +
                  "            \"value\": \"0.1—0.15Mpa\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"7\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"阻燃等级\",\n" +
                  "            \"value\": \"有机板B1级，无机板A级\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"8\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"安装方式\",\n" +
                  "            \"value\": \"粘贴+锚固\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"9\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M00/01/62/rBCwI1vPxUiAUJeqAABSMxyMaCI323.jpg\",\n" +
                  "        \"supplierId\": \"5442ea93687315fad5cce2024e85dca5\",\n" +
                  "        \"spuId\": \"e2d2a8c0ed3ee0e087d73b67c52e8bb5\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"威尔达\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"3a8b889c291c73246df557fd7cf25ffd\",\n" +
                  "        \"itemCode\": \"10003195\",\n" +
                  "        \"itemName\": \"氟碳金属面层10mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 96.00,\n" +
                  "        \"maxPrice\": 96.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"f475fe0c3f9550e93cd2aa67f9150be3\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"10mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M00/00/76/rBCwJFta6eiAQF2YAAUpwVrhODY226.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"4561d99f0f2f9bd25505787d7a20983e\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"be189c5a37dd32ae2ec1fcb4b484947e\",\n" +
                  "        \"itemCode\": \"10003205\",\n" +
                  "        \"itemName\": \"质感仿石材（水包砂）8mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 108.00,\n" +
                  "        \"maxPrice\": 108.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"b3d0a607c88958a9116f4a3e7abc439d\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"8mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M01/00/76/rBCwJFta7f6ARY99AArMwx09LRI347.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"0943689f48c773f48e6fefe959969009\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"ee24a259bf08d6c9ee9966f78aee95cd\",\n" +
                  "        \"itemCode\": \"10003188\",\n" +
                  "        \"itemName\": \"氟碳实色面层6mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 82.00,\n" +
                  "        \"maxPrice\": 82.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"a8d91b9d63ee7c4a054e54e73b0a663f\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"6mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M01/00/75/rBCwJFta4fOAMiwkABW67fBj-Ik353.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"a588d439db7f1e83fa09f7b2f6f85019\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      },\n" +
                  "      {\n" +
                  "        \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "        \"itemId\": \"105feb2260ee7233d08f31d5a041f51f\",\n" +
                  "        \"itemCode\": \"10003190\",\n" +
                  "        \"itemName\": \"氟碳实色面层10mm\",\n" +
                  "        \"itemShortName\": null,\n" +
                  "        \"primaryUnitCode\": \"kg\",\n" +
                  "        \"unitName\": \"千克\",\n" +
                  "        \"secondaryUnitCode\": null,\n" +
                  "        \"minPrice\": 92.00,\n" +
                  "        \"maxPrice\": 92.00,\n" +
                  "        \"minMemberPrice\": -1.00,\n" +
                  "        \"maxMemberPrice\": -1.00,\n" +
                  "        \"itemLine\": [\n" +
                  "          {\n" +
                  "            \"itemLineId\": \"995e4f3f522e9d23b1e702333a7da69f\",\n" +
                  "            \"salePrice1\": null,\n" +
                  "            \"salePrice2\": null,\n" +
                  "            \"salePrice3\": null,\n" +
                  "            \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "            \"areaName\": \"全国\",\n" +
                  "            \"areaCode\": \"CN\",\n" +
                  "            \"miniOrderNum\": null,\n" +
                  "            \"displayFlag\": \"Y\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"specListJson\": \"\",\n" +
                  "        \"specList\": [\n" +
                  "          {\n" +
                  "            \"name\": \"品牌\",\n" +
                  "            \"value\": \"东方聚能\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"1\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"规格\",\n" +
                  "            \"value\": \"1220*2440\",\n" +
                  "            \"priceFlag\": \"N\",\n" +
                  "            \"orderSort\": \"2\"\n" +
                  "          },\n" +
                  "          {\n" +
                  "            \"name\": \"面板厚度\",\n" +
                  "            \"value\": \"10mm\",\n" +
                  "            \"priceFlag\": \"Y\",\n" +
                  "            \"orderSort\": \"3\"\n" +
                  "          }\n" +
                  "        ],\n" +
                  "        \"pictureList\": null,\n" +
                  "        \"pictureUrl\": \"group1/M01/00/75/rBCwJFta5DqAAx4hABcEn2UeCG0226.jpg\",\n" +
                  "        \"supplierId\": \"8bb70526d19cc59b90845e3ba5aa26d0\",\n" +
                  "        \"spuId\": \"a588d439db7f1e83fa09f7b2f6f85019\",\n" +
                  "        \"brand\": {\n" +
                  "          \"brandId\": null,\n" +
                  "          \"brandName\": \"东方聚能\",\n" +
                  "          \"firstLetter\": null,\n" +
                  "          \"score\": null\n" +
                  "        }\n" +
                  "      }\n" +
                  "    ],\n" +
                  "    \"currentPage\": 1,\n" +
                  "    \"totalSize\": 18,\n" +
                  "    \"categoryLevel\": {\n" +
                  "      \"levelOne\": [\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200744500276\",\n" +
                  "          \"categoryCode\": \"11\",\n" +
                  "          \"categoryName\": \"装配式建筑\",\n" +
                  "          \"categoryLevel\": \"1\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200744506801\",\n" +
                  "          \"categoryCode\": \"12\",\n" +
                  "          \"categoryName\": \"建筑工程\",\n" +
                  "          \"categoryLevel\": \"1\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200744503177\",\n" +
                  "          \"categoryCode\": \"13\",\n" +
                  "          \"categoryName\": \"设备设施\",\n" +
                  "          \"categoryLevel\": \"1\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200744505483\",\n" +
                  "          \"categoryCode\": \"14\",\n" +
                  "          \"categoryName\": \"精装修\",\n" +
                  "          \"categoryLevel\": \"1\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200744507882\",\n" +
                  "          \"categoryCode\": \"15\",\n" +
                  "          \"categoryName\": \"园林景观\",\n" +
                  "          \"categoryLevel\": \"1\"\n" +
                  "        }\n" +
                  "      ],\n" +
                  "      \"levelTwo\": [\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200749318745\",\n" +
                  "          \"categoryCode\": \"12001\",\n" +
                  "          \"categoryName\": \"主体结构材料\",\n" +
                  "          \"categoryLevel\": \"2\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200749311998\",\n" +
                  "          \"categoryCode\": \"12002\",\n" +
                  "          \"categoryName\": \"竹木材料\",\n" +
                  "          \"categoryLevel\": \"2\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200749313756\",\n" +
                  "          \"categoryCode\": \"12003\",\n" +
                  "          \"categoryName\": \"防水材料\",\n" +
                  "          \"categoryLevel\": \"2\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200749312785\",\n" +
                  "          \"categoryCode\": \"12004\",\n" +
                  "          \"categoryName\": \"保温隔音材料\",\n" +
                  "          \"categoryLevel\": \"2\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200749312659\",\n" +
                  "          \"categoryCode\": \"12005\",\n" +
                  "          \"categoryName\": \"管材管件\",\n" +
                  "          \"categoryLevel\": \"2\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200749316725\",\n" +
                  "          \"categoryCode\": \"12007\",\n" +
                  "          \"categoryName\": \"幕墙材料\",\n" +
                  "          \"categoryLevel\": \"2\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200749318806\",\n" +
                  "          \"categoryCode\": \"12008\",\n" +
                  "          \"categoryName\": \"门窗及门窗五金\",\n" +
                  "          \"categoryLevel\": \"2\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200749312854\",\n" +
                  "          \"categoryCode\": \"12010\",\n" +
                  "          \"categoryName\": \"强电材料\",\n" +
                  "          \"categoryLevel\": \"2\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200749312708\",\n" +
                  "          \"categoryCode\": \"12011\",\n" +
                  "          \"categoryName\": \"油漆涂料\",\n" +
                  "          \"categoryLevel\": \"2\"\n" +
                  "        }\n" +
                  "      ],\n" +
                  "      \"levelThree\": [\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200757010109\",\n" +
                  "          \"categoryCode\": \"12004001\",\n" +
                  "          \"categoryName\": \"岩棉板\",\n" +
                  "          \"categoryLevel\": \"3\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"IMPL0000000000201804200757011990\",\n" +
                  "          \"categoryCode\": \"12004009\",\n" +
                  "          \"categoryName\": \"一体板\",\n" +
                  "          \"categoryLevel\": \"3\"\n" +
                  "        },\n" +
                  "        {\n" +
                  "          \"categoryId\": \"1bc4395d3376013d5a17672663cdee86\",\n" +
                  "          \"categoryCode\": \"12004010\",\n" +
                  "          \"categoryName\": \"泡沫陶瓷板\",\n" +
                  "          \"categoryLevel\": \"3\"\n" +
                  "        }\n" +
                  "      ]\n" +
                  "    }\n" +
                  "  },\n" +
                  "  \"token\": null\n" +
                  "}";

//          List<String> obj= (List<String>) JSONPath.read(jstr,"userId");
//          System.out.println(obj.getClass());
//          System.out.println(obj.toString());
//
//          System.out.println(obj.get(0).getClass());

          JSONArray obj1= (JSONArray) JSONPath.read(jstr,"$.data.itemList.itemId");
          System.out.println(obj1);
          System.out.println(obj1.size());

          JSONArray obj2= (JSONArray) JSONPath.read(jstr,"$.data.itemList.minPrice");
          System.out.println(obj2.getClass());
          System.out.println(obj2.size());


          JSONArray obj3= (JSONArray) JSONPath.read(jstr,"$.data.itemList.itemLine.itemLineId");
          System.out.println(obj3.getClass());
          System.out.println(obj3.size());


          JSONArray obj4= (JSONArray) JSONPath.read(jstr,"$.data.itemList.itemLine.areaCode");
          System.out.println(obj4.getClass());
          System.out.println(obj4.size());

          Object[][] objects = null;
          objects=new Object[obj1.size()][4];
          for(int i = 0;i<obj1.size();i++){
              objects[i][0]=obj1.get(i);
              objects[i][1]=obj2.get(i);
              objects[i][2]=obj3.get(i);
              objects[i][3]=obj4.get(i);

          }
          System.out.println(objects.toString());
          for (Object[] oo:objects){
              for (Object ooo:oo){
                  System.out.println(ooo);
              }
          }


      }
}
