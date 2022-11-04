import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const SearchbarContainer = styled.form`
  box-sizing: border-box;
  padding: 0 8px;
  position: relative;
  display: flex;
  font-family: inherit;
`;

const SearchbarInput = styled.input`
  box-sizing: border-box;
  max-width: ${(props) => props.width || '800px'};
  height: ${(props) => props.height || '30px'};

  border: 1px solid #babfc4;
  border-radius: 3px;
  padding-left: 30px;

  &:focus {
    outline: none;
    border: 1px solid #6bbbf7;
    box-shadow: 0px 0px 0px 4px #d8e5f2;
  }
`;

const SearchIcon = styled.div`
  position: absolute;
  left: 15px;
  top: 7px;
  svg {
    color: #81878c;
  }
`;

const HeaderSearchbarContainer = styled(SearchbarContainer)`
  flex-grow: 1;
  max-width: 756px;
  position: relative;
  @media screen and (max-width: 640px) {
    display: none;
  }
`;

const HeaderSearchbarInput = styled(SearchbarInput)`
  width: 100%;
  height: 33px;
  position: relative;
  @media screen and (max-width: 640px) {
    display: none;
  }
`;
const HeaderSearchIcon = styled(SearchIcon)`
  @media screen and (max-width: 640px) {
    position: static;
  }
`;

const SearchDropdown = styled.div`
  width: 98%;
  min-width: 420px;
  position: absolute;
  top: 38px;
  left: 1%;
  background-color: white;
  border: 1px solid #e3e6e8;
  border-radius: 5px;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);
  display: flex;
  flex-direction: column;
  padding: 12px 12px 0 12px;
`;

const HintsWrapper = styled.div`
  display: flex;
`;

const SearchDropdownHints = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`;

const Hint = styled.div`
  margin-bottom: 12px;
  font-size: 13px;
  line-height: 17px;
  .name {
    font-family: ui-monospace, 'Cascadia Mono', 'Segoe UI Mono',
      'Liberation Mono', Menlo, Monaco, Consolas, monospace;
    color: #0c0d00;
    margin-right: 3px;
  }
  .ex {
    font-family: inherit;
    color: #6a737c;
  }
`;

const SearchDropdownBottom = styled.div`
  width: 100%;
  height: 52px;
  border-top: 1px solid #e3e6e8;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 11px;

  button.helpbtn {
    width: 92.13px;
    height: 27.88px;
    color: #39739d;
    background-color: #e1ecf4;
    padding: 6.6px;
    border: 1px solid #39739d;
    border-radius: 3px;
    &:hover {
      background-color: hsl(205, 57%, 81%);
      cursor: pointer;
    }
  }
  div {
    color: #0074cc;
    font-weight: 600;
    &:hover {
      cursor: pointer;
      color: #0a95ff;
    }
  }
`;

const HeaderSearchbar = ({ handleClickMenu, clickedMenu }) => {
  const navigate = useNavigate();
  const handleSubmitQuestionForm = () => {
    navigate('/questions');
  };
  return (
    <HeaderSearchbarContainer id="search" onSubmit={handleSubmitQuestionForm}>
      <HeaderSearchbarInput
        type="text"
        placeholder="Search..."
        onFocus={() => handleClickMenu('search')}
        onBlur={() => handleClickMenu(null)}
      ></HeaderSearchbarInput>
      <HeaderSearchIcon>
        <svg
          aria-hidden="true"
          className="svg-icon iconSearch"
          width="18"
          height="18"
          viewBox="0 0 18 18"
        >
          <path
            style={{ fill: 'rgb(131, 140, 149)' }}
            d="m18 16.5-5.14-5.18h-.35a7 7 0 1 0-1.19 1.19v.35L16.5 18l1.5-1.5ZM12 7A5 5 0 1 1 2 7a5 5 0 0 1 10 0Z"
          />
        </svg>
      </HeaderSearchIcon>
      {clickedMenu === 'search' ? (
        <SearchDropdown>
          <HintsWrapper>
            <SearchDropdownHints>
              <Hint>
                <span className="name">[tag]</span>
                <span className="ex">search within a tag</span>
              </Hint>
              <Hint>
                <span className="name">user:1234</span>
                <span className="ex">search bu author</span>
              </Hint>
              <Hint>
                <span className="name">{`"words here"`}</span>
                <span className="ex">exprect phrase</span>
              </Hint>
              <Hint>
                <span className="name">{`"collective"`}</span>
                <span className="ex">collective content</span>
              </Hint>
            </SearchDropdownHints>
            <SearchDropdownHints>
              <Hint>
                <span className="name">answers:0</span>
                <span className="ex">unanswered questions</span>
              </Hint>
              <Hint>
                <span className="name">score:3</span>
                <span className="ex">posts with a 3+ score</span>
              </Hint>
              <Hint>
                <span className="name">is:question</span>
                <span className="ex">type of post</span>
              </Hint>
              <Hint>
                <span className="name">isaccepted:yes</span>
                <span className="ex">search within status</span>
              </Hint>
            </SearchDropdownHints>
          </HintsWrapper>
          <SearchDropdownBottom>
            <button className="helpbtn">Ask a question</button>
            <div>Search help</div>
          </SearchDropdownBottom>
        </SearchDropdown>
      ) : null}
    </HeaderSearchbarContainer>
  );
};

const Searchbar = ({ width, height, placeholder }) => {
  const heightpx = height.slice(0, -2);
  return (
    <SearchbarContainer>
      <SearchbarInput
        width={width}
        height={height}
        type="text"
        placeholder={placeholder}
      ></SearchbarInput>
      <SearchIcon heightpx={heightpx}>
        <svg
          aria-hidden="true"
          className="svg-icon iconSearch"
          width="18"
          height="18"
          viewBox="0 0 18 18"
        >
          <path
            style={{ fill: 'rgb(131, 140, 149)' }}
            d="m18 16.5-5.14-5.18h-.35a7 7 0 1 0-1.19 1.19v.35L16.5 18l1.5-1.5ZM12 7A5 5 0 1 1 2 7a5 5 0 0 1 10 0Z"
          />
        </svg>
      </SearchIcon>
    </SearchbarContainer>
  );
};

export { Searchbar, HeaderSearchbar, SearchbarInput };
