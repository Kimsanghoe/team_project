import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import PersonIcon from '@mui/icons-material/Person';
import { useMemberStore } from '../../stores';
import { useCookies } from 'react-cookie';

export default function Navigation() {
    const [cookies, setCookies] = useCookies();
    const { member, removeMember } = useMemberStore();

    const logOutHandler = () => {
        setCookies('token', '', { expires: new Date() });
        removeMember();
    };

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="fixed">
                <Toolbar>
                    <IconButton size="large" edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        News
                    </Typography>
                    {member ? (
                        <IconButton color="inherit" onClick={() => logOutHandler()}>
                            <PersonIcon />
                        </IconButton>
                    ) : (
                        <Button color="inherit">Login</Button>
                    )}
                </Toolbar>
            </AppBar>
        </Box>
    );
}
