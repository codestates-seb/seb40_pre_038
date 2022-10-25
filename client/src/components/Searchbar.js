import styled from 'styled-components';

const SearchbarContainer = styled.form`
  box-sizing: border-box;
  padding: 0 8px;
  position: relative;
`;

const SearchbarInput = styled.input`
  box-sizing: border-box;
  width: ${(props) => props.width || '756px'};
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
  svg {
    position: absolute;
    left: 15px;
    top: 8px;
    color: black;
    font-size: 20px;
    color: #81878c;
  }
`;

const Searchbar = ({ width, height, placeholder }) => {
  return (
    <SearchbarContainer>
      <SearchbarInput
        width={width}
        height={height}
        type="text"
        placeholder={placeholder}
      ></SearchbarInput>
      <SearchIcon>
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

export default Searchbar;
