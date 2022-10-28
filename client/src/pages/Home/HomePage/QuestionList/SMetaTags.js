import { Link } from 'react-router-dom';
import styled from 'styled-components';

const SummaryMetaTagsWrapper = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
  line-height: 18px;
  float: left;
`;

const TagList = styled.ul`
  display: inline !important;
  list-style: none !important;
  margin-left: 0 !important;
`;

const TagListsItem = styled.li`
  display: inline !important;
  margin-right: 4px !important;
`;

const TagListsItemLink = styled(Link)`
  font-size: 12px;
  color: #39739d;
  background-color: #e1ecf4;
  border-color: transparent;
  display: inline-block;
  padding: 0.4em 0.5em;
  margin: 2px 2px 2px 0;
  line-height: 1;
  white-space: nowrap;
  text-decoration: none;
  text-align: center;
  border-width: 1px;
  border-style: solid;
  border-radius: 3px;
  margin-top: 0 !important;
  cursor: pointer;
  user-select: auto;

  :hover {
    text-decoration: none;
    color: #2c5877;
    background-color: #d0e3f1;
    border-color: transparent;
  }
`;

const SMetaTags = () => {
  return (
    <SummaryMetaTagsWrapper>
      <TagList>
        <TagListsItem>
          <TagListsItemLink
            to="/questions/tagged/python"
            className="js-tagname-python"
            title="show questions tagged 'python'"
            aria-label="show questions tagged 'python'"
            rel="tag"
            aria-labelledby="python-container"
          >
            python
          </TagListsItemLink>
        </TagListsItem>
        <TagListsItem>
          <TagListsItemLink
            to="/questions/tagged/variables"
            className="js-tagname-variables"
            title="show questions tagged 'variables'"
            aria-label="show questions tagged 'variables'"
            rel="tag"
            aria-labelledby="variables-container"
          >
            variables
          </TagListsItemLink>
        </TagListsItem>
      </TagList>
    </SummaryMetaTagsWrapper>
  );
};

export default SMetaTags;
