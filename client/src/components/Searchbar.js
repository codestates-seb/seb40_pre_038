import styled from 'styled-components';

const SearchbarContainer = styled.form`
  box-sizing: border-box;
  padding: 0 8px;
  position: relative;
  display: flex;
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
  @media screen and (max-width: 640px) {
    display: none;
  }
`;

const HeaderSearchbarInput = styled(SearchbarInput)`
  width: 100%;
  height: 33px;
  @media screen and (max-width: 640px) {
    display: none;
  }
`;
const HeaderSearchIcon = styled(SearchIcon)`
  @media screen and (max-width: 640px) {
    position: static;
  }
`;

const HeaderSearchbar = () => {
  return (
    <HeaderSearchbarContainer>
      <HeaderSearchbarInput
        type="text"
        placeholder="Search..."
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

export { Searchbar, HeaderSearchbar };
