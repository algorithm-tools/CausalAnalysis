# CausalAnalysis

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=socialflat-square&)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Total Lines](https://img.shields.io/github/stars/algorithm-tools/CausalAnalysis?style=socialflat-square&label=stars)](https://github.com/algorithm-tools/CausalAnalysis/stargazers)
[![CN doc](https://img.shields.io/badge/文档-中文版-blue.svg?style=socialflat-square&)](README_zh_CN.md)
[![EN doc](https://img.shields.io/badge/document-English-blue.svg?style=socialflat-square&)](README.md)


# 简介
指标归因分析、因果推断的算法实现，使用Java.帮助快速寻找指标根因

# 特性
**已支持**:
- 通过 JS 散点计算快速定位根本原因维度.
- 支持对指标进行贡献度拆解，以快速定位关键因素.

**计划中**:
- 相关性分析.
- 因果推断.

# Demos
- [CausalAnalysis-Demos](https://github.com/algorithm-tools/CausalAnalysis/tree/main/src/test/java/org/algorithmtools/ca4j/example)

# For Developers
## Using ad4j
- add to maven pom:
```xml
<dependency>
    <groupId>org.algorithmtools</groupId>
    <artifactId>ca4j</artifactId>
    <version>${version}</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/ca4j-${version}.jar</systemPath>
</dependency>

```

- business use:
  Example of the test module under the example package
```java
public class ContributionAnalysisExample {

  public static void main(String[] args) {
    // 1. Transfer biz data to indicator series info
    long currentTime = System.currentTimeMillis();
    List<IndicatorSeries> indicatorSeriesX0 = Arrays.asList(new IndicatorSeries(currentTime - 86400000 + 1, 1, "A")
            , new IndicatorSeries(currentTime - 86400000 + 2, 2, "B")
            , new IndicatorSeries(currentTime - 86400000 + 3, 3, "C")
            , new IndicatorSeries(currentTime - 86400000 + 4, 6, "D")
            , new IndicatorSeries(currentTime - 86400000 + 5, 5, "E")
    );
    List<IndicatorSeries> indicatorSeriesX1 = Arrays.asList(new IndicatorSeries(currentTime + 1, 1, "A")
            , new IndicatorSeries(currentTime + 2, 1.5, "B")
            , new IndicatorSeries(currentTime + 3, 3, "C")
            , new IndicatorSeries(currentTime + 4, 8, "D")
            , new IndicatorSeries(currentTime + 5, 3, "E")
    );
    IndicatorPairSeries series = new IndicatorPairSeries("i-1", "i-1-name", IndicatorStatType.Unique_Continuity, indicatorSeriesX1, indicatorSeriesX0);

    // 2. Get a PlusContributionAnalysisEngin object
    // PlusContributionAnalysisEngin the calculation of contributions to indicators of the additive/subtractive type.
    // MultiplyContributionAnalysisEngin the calculation of the contribution of indicators to the multiplication type.
    // DivisionContributionAnalysisEngin the calculation of the contribution to the indicators of division type
    PlusContributionAnalysisEngin engin = new PlusContributionAnalysisEngin(CausalAnalysisContext.createDefault());

    // 3. analyse
    ContributionResult result = engin.analyse(series);

    // 4. Business process analysis result. Like Records,Alarms,Print,Deep analysis...
    System.out.println(result);
  }

}
```
Print result:
```text
Overview:17.0-->16.5	ChangeValue(-0.5)	ChangeRate(-0.029411764705882353)
FactorTermContribution:
FactorTerm:A	1.0-->1.0	ChangeValue(ChangeRate):0.0(0.0)	ContributionValue(ContributionRate):0.0(0.0)	 ContributionProportion:0.0
FactorTerm:B	2.0-->1.5	ChangeValue(ChangeRate):-0.5(-0.25)	ContributionValue(ContributionRate):-0.5(-0.029411764705882353)	 ContributionProportion:0.1111111111111111
FactorTerm:C	3.0-->3.0	ChangeValue(ChangeRate):0.0(0.0)	ContributionValue(ContributionRate):0.0(0.0)	 ContributionProportion:0.0
FactorTerm:D	6.0-->8.0	ChangeValue(ChangeRate):2.0(0.3333333333333333)	ContributionValue(ContributionRate):2.0(0.11764705882352941)	 ContributionProportion:0.4444444444444444
FactorTerm:E	5.0-->3.0	ChangeValue(ChangeRate):-2.0(-0.4)	ContributionValue(ContributionRate):-2.0(-0.11764705882352941)	 ContributionProportion:0.4444444444444444
Contribution Sum:-0.5(-0.02941176470588236)
```

# 参与贡献
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](https://github.com/algorithm-tools/CausalAnalysis/pulls)

欢迎加入共建共赢， 贡献流程请参考：[参与贡献](https://github.com/algorithm-tools/CausalAnalysis/blob/main/docs/developer_guide/Contribution_Guide.md).

感谢所有做出贡献的人！

[![Contributors](https://contrib.rocks/image?repo=algorithm-tools/CausalAnalysis)](https://github.com/algorithm-tools/CausalAnalysis/graphs/contributors)


# 获得帮助

- 创建 issue，并描述清晰