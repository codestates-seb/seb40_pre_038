import styled from 'styled-components';

const SearchTipsTable = styled.table`
  margin-bottom: 24px !important;
  display: 'table';
  width: 100%;
  max-width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  font-size: 13px;

  th,
  td {
    padding: 8px;
    border-top: 1px solid #d6d9dc;
    border-left: 1px solid #d6d9dc;
    border-right: 1px solid #d6d9dc;
    vertical-align: middle;
    color: #3b4045;
    text-align: left;
  }

  th {
    font-weight: bold;
    color: #0c0d0e;
  }

  thead th {
    vertical-align: bottom;
    white-space: nowrap;
    background-color: #f8f9f9;
    line-height: calc((13 + 2) / 13);
  }

  th,
  td {
    border-left-color: transparent;
    border-right-color: transparent;
  }

  tr:last-of-type td,
  tr:last-of-type th {
    border-bottom: 1px solid #d6d9dc;
  }

  thead th {
    border-top-color: transparent;
    border-bottom: #babfc4;
    background-color: transparent;
    text-transform: initial;
    font-size: inherit;
    letter-spacing: initial;
  }

  &.s-table--cell2 {
    width: 16.66666667%;
  }

  &.ff-mono {
    font-family: ui-monospace, 'Cascadia Mono', 'Segoe UI Mono',
      'Liberation Mono', Menlo, Monaco, Consolas, monospace !important;
  }

  &.fc-light {
    color: #6a737c !important;
  }
`;

const SearchTips = () => {
  return (
    <SearchTipsTable>
      <thead>
        <tr>
          <th className="s-table--cell2" scope="col">
            Search type
          </th>
          <th scope="col">Search syntax</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row">Tags</th>
          <td className="ff-mono">[tag]</td>
        </tr>
        <tr>
          <th scope="row">Exact</th>
          <td className="ff-mono">&quot;words here&quot;</td>
        </tr>
        <tr>
          <th scope="row">Author</th>
          <td className="ff-mono">
            user:1234
            <br />
            user:me <span className="fc-light">(yours)</span>
          </td>
        </tr>
        <tr>
          <th scope="row">Score</th>
          <td className="ff-mono">
            score:3 <span className="fc-light">(3+)</span>
            <br />
            score:0 <span className="fc-light">(none)</span>
          </td>
        </tr>
        <tr>
          <th scope="row">Answers</th>
          <td className="ff-mono">
            answers:3 <span className="fc-light">(3+)</span>
            <br />
            answers:0 <span className="fc-light">(none)</span>
            <br />
            isaccepted:yes
            <br />
            hasaccepted:no
            <br />
            inquestion:1234
          </td>
        </tr>
        <tr>
          <th scope="row">Views</th>
          <td className="ff-mono">views:250</td>
        </tr>
        <tr>
          <th scope="row">Code</th>
          <td className="ff-mono">code:&quot;if (foo != bar)&quot;</td>
        </tr>
        <tr>
          <th scope="row">Sections</th>
          <td className="ff-mono">
            title:apples
            <br />
            body:&quot;apples oranges&quot;
          </td>
        </tr>
        <tr>
          <th scope="row">URL</th>
          <td className="ff-mono">url:&quot;*.example.com&quot;</td>
        </tr>
        <tr>
          <th scope="row">Saves</th>
          <td className="ff-mono">in:saves</td>
        </tr>
        <tr>
          <th scope="row">Status</th>
          <td className="ff-mono">
            closed:yes
            <br />
            duplicate:no
            <br />
            migrated:no
            <br />
            wiki:no
          </td>
        </tr>
        <tr>
          <th scope="row">Types</th>
          <td className="ff-mono">
            is:question
            <br />
            is:answer
            <br />
            is:article
          </td>
        </tr>
        <tr>
          <th scope="row">Exclude</th>
          <td className="ff-mono">
            -[tag]
            <br />
            -apples
          </td>
        </tr>
        <tr>
          <th scope="row">Collective</th>
          <td className="ff-mono">collective:&quot;Name&quot;</td>
        </tr>
      </tbody>
    </SearchTipsTable>
  );
};

export default SearchTips;
